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
        createFile(testId, testAnswer.code)
    }

    private fun createFile(testId: String, code: String) {
        val currentUserId = authService.getCurrentUser().id ?: throw IllegalArgumentException("userId must not be null")
        val testFile = File(generateFilename(currentUserId, testId))
        testFile.createNewFile()
        val fileWriter = PrintWriter(testFile)
        fileWriter.use {
            it.write(code)
        }
    }

    private fun generateFilename(userId: String, testId: String): String {
        val dirPath = "${userFilesDir}/${userId}"
        checkDir(dirPath)
        return "${dirPath}/${testId}_${System.currentTimeMillis()}.kt"
    }

    private fun checkDir(dirPath: String) {
        val dir = File(dirPath)
        if (!dir.exists()) {
            dir.mkdirs()
        }
    }
}