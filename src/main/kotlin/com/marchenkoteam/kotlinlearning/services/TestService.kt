package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.dto.TestDto
import com.marchenkoteam.kotlinlearning.exceptions.BadRequestException
import com.marchenkoteam.kotlinlearning.forms.TestAnswer
import com.marchenkoteam.kotlinlearning.forms.TestForm
import com.marchenkoteam.kotlinlearning.models.Test
import com.marchenkoteam.kotlinlearning.models.UserTest
import com.marchenkoteam.kotlinlearning.repositories.TestRepository
import com.marchenkoteam.kotlinlearning.repositories.UserTestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class TestService @Autowired constructor(private val authService: AuthService,
                                         private val compilerService: CompilerService,
                                         private val testRepository: TestRepository,
                                         private val userTestRepository: UserTestRepository) {

    fun findById(id: String): TestDto {
        val test = testRepository.findById(id)
                .orElseThrow { BadRequestException("No such test.") }
        return TestDto.from(test)
    }

    fun findAll() = testRepository.findAll().map(TestDto.Companion::from)

    fun save(testForm: TestForm): TestDto? {
        var test = Test.from(testForm)
        test = testRepository.save(test)
        return TestDto.from(test)
    }

    fun deleteById(id: String) {
        testRepository.deleteById(id)
    }

    fun checkTest(testId: String, testAnswer: TestAnswer): UserTest {
        val currentUserId = authService.getCurrentUser().id
                ?: throw IllegalArgumentException("Current user doesn't have an id")
        val test = testRepository.findById(testId)
                .orElseThrow { IllegalArgumentException("There's no test with id = $testId") }
        val testStatus = compilerService.runTest(currentUserId, testId, testAnswer, test)
        val userTest = userTestRepository.findByUserIdAndTestId(currentUserId, testId)
                .map {
                    it.code = testAnswer.code
                    it.testStatus = testStatus
                    it
                }
                .orElse(UserTest(null, currentUserId, testId, testAnswer.code, testStatus))
        return userTestRepository.save(userTest)
    }
}