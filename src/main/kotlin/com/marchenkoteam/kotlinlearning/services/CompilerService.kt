package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.forms.TestAnswer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.io.File
import java.io.PrintWriter

@Service
class CompilerService @Autowired constructor(private val authService: AuthService) {

    @Value("\${app.userFilesDir}")
    private lateinit var userFilesDir: String

    fun runTest(testId: String, testAnswer: TestAnswer) {
        val filename = createFile(testId, testAnswer.code)
        compileFile(filename)
    }

    private fun createFile(testId: String, code: String): String {
        val currentUserId = authService.getCurrentUser().id ?: throw IllegalArgumentException("userId must not be null")
        val filename = generateFilename(testId)
        val testFile = File("${getUserDirPath(currentUserId)}/$filename.kt")
        testFile.createNewFile()
        val fileWriter = PrintWriter(testFile)
        fileWriter.use {
            it.write(code)
        }
        return filename
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

    private fun compileFile(filename: String) {
        val runtime = Runtime.getRuntime()
        val currentUserId = authService.getCurrentUser().id
                ?: throw java.lang.IllegalArgumentException("There is no id in user entity")
        val file = File(getUserDirPath(currentUserId))
        println(file.exists())
        val process = runtime.exec("cmd.exe /c kotlinc $filename.kt -d $filename.jar", null, file)
        process.waitFor()
    }
}