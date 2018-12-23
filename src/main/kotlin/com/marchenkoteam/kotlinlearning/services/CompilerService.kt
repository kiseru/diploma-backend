package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.models.Test
import com.marchenkoteam.kotlinlearning.models.TestStatus
import com.marchenkoteam.kotlinlearning.models.UserTest
import com.marchenkoteam.kotlinlearning.repositories.UserThemeRankRepository
import com.marchenkoteam.kotlinlearning.security.details.UserDetailsImpl
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

@Service
class CompilerService(private val userTestService: UserTestService,
                      private val userThemeRankRepository: UserThemeRankRepository) {

    private final val path = "D:\\Repos\\testic-backend\\src\\main\\resources\\temp"

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
                    }.inputStream.bufferedReader().readText()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun checkTest(test: Test) {
        val inputFile = File("$path\\inputFile")
        inputFile.createNewFile()
        inputFile.writeText(test.inputData)
        val auth = SecurityContextHolder.getContext().authentication
        val userId = (auth.details as UserDetailsImpl).user.id
        val code = userTestService.findByTestIdAndUserId(test.id, userId).code
    }

    fun compile(code: String) : String? {
        val sourceFile = File("$path\\sourceFile")
        sourceFile.createNewFile()
        sourceFile.writeText(code)
        return runCommand("kotlinc sourceFile -include-runtime -d source.jar && java -jar source.jar")
    }

}