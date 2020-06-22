package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.forms.TestAnswer
import com.marchenkoteam.kotlinlearning.models.Test
import com.marchenkoteam.kotlinlearning.models.TestStatus
import com.marchenkoteam.kotlinlearning.models.UnitTest
import com.marchenkoteam.kotlinlearning.repositories.UnitTestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.*

@Service
class CompilerService @Autowired constructor(private val unitTestRepository: UnitTestRepository) {

    @Value("\${app.userFilesDir}")
    private lateinit var userFilesDir: String

    fun runTest(currentUserId: String, testId: String, testAnswer: TestAnswer, test: Test): TestStatus {
        val userDirPath = getUserDirPath(currentUserId)
        val filename = generateFilename(testId)
        createFile(userDirPath, filename, testAnswer.code)
        compileFile(userDirPath, filename)
        val testResult = checkAnswer(userDirPath, filename, test)
        cleanTestData(userDirPath, filename)
        return testResult
    }

    private fun createFile(userDirPath: String, filename: String, code: String) {
        val testFile = File("$userDirPath/$filename.kt")
        testFile.createNewFile()
        val fileWriter = PrintWriter(testFile)
        fileWriter.use {
            it.write(code)
        }
    }

    private fun generateFilename(testId: String): String {
        return "${testId}_${System.currentTimeMillis()}"
    }

    private fun getUserDirPath(userId: String): String {
        val dirPath = "$userFilesDir/$userId"
        checkDir(dirPath)
        return dirPath
    }

    private fun checkDir(dirPath: String) {
        val dir = File(dirPath)
        if (!dir.exists()) {
            dir.mkdirs()
        }
    }

    private fun compileFile(userDirPath: String, filename: String) {
        val runtime = Runtime.getRuntime()
        val path = File(userDirPath)
        val process = runtime.exec("cmd.exe /c kotlinc $filename.kt -d $filename.jar", null, path)
        process.waitFor()
    }

    private fun checkAnswer(userDirPath: String, filename: String, test: Test): TestStatus {
        val runtime = Runtime.getRuntime()
        val path = File(userDirPath)
        val unitTests = unitTestRepository.findAllByTestId(test.id!!)
        val passed = unitTests.asSequence().map {
            val process = runtime.exec("cmd.exe /c java -jar $filename.jar", null, path)
            val inputData = it.inputData
            if (inputData != null) {
                writeInputData(process.outputStream, inputData)
            }

            val processResult = process.waitFor()
            if (processResult != 0) {
                 TestStatus.FAILED
            } else {
                checkOutputData(process.inputStream, it.outputData)
            }
        }
                .all { it == TestStatus.PASSED }

        return if (passed) {
            TestStatus.PASSED
        } else {
            TestStatus.FAILED
        }
    }

    private fun cleanTestData(userDirPath: String, filename: String) {
        val ktFile = File("$userDirPath/$filename.kt")
        ktFile.delete()

        val jarFile = File("$userDirPath/$filename.jar")
        jarFile.delete()
    }

    private fun writeInputData(outputStream: OutputStream, inputData: String) {
        outputStream.use {
            it.write(inputData.toByteArray())
        }
    }

    private fun checkOutputData(inputStream: InputStream, expectedData: String): TestStatus {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val expectedLines = expectedData.split("\n")
        reader.use {
            expectedLines.forEach { expectedLine ->
                if (!it.ready()) {
                    return TestStatus.FAILED
                }

                val line = reader.readLine()
                if (line != expectedLine) {
                    return TestStatus.FAILED
                }
            }
        }

        return TestStatus.PASSED
    }
}