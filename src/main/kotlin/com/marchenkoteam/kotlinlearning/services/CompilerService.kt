package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.models.TestStatus
import com.marchenkoteam.kotlinlearning.models.UserTest
import com.marchenkoteam.kotlinlearning.repositories.UserRepository
import org.springframework.stereotype.Service
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

@Service
class CompilerService(private val userRepository: UserRepository) {

    private final val path = ".\\src\\main\\resources\\compiler_temp"

    fun runCommand(command: String,
                   timeoutAmount: Long = 60,
                   timeoutUnit: TimeUnit = TimeUnit.SECONDS): String? {
        return try {
            ProcessBuilder(*command.split("\\s".toRegex()).toTypedArray())
                    .directory(File(path))
                    .redirectOutput(ProcessBuilder.Redirect.PIPE)
                    .redirectError(ProcessBuilder.Redirect.PIPE)
                    .start().apply {
                        waitFor(timeoutAmount, timeoutUnit)
                    }.inputStream.bufferedReader().readText().dropLast(2)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
        //clearing folder
        finally {
            File(path).deleteRecursively()
        }
    }

    fun checkTest(userTest: UserTest) {
//        File(path).mkdir()
////        val test = userTest.test
////        val user = userTest.user
//        val inputFile = File("$path\\inputFile")
//        inputFile.createNewFile()
////        inputFile.writeText(test.inputData)
////        val code = userTest.code
////        if (test.outputData == compile(code)) {
////            userTest.status = TestStatus.PASSED
////        }
////        userRepository.save(user)
    }

    fun compile(code: String): String? {
        val sourceFile = File("$path\\sourceFile.kt")
        sourceFile.createNewFile()
        sourceFile.writeText(code)
        return runCommand("cmd /c kotlinc sourceFile.kt -include-runtime -d source.jar && java -jar source.jar")
    }
}