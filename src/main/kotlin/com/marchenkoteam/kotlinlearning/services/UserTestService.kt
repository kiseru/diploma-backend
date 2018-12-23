package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.forms.UserTestForm
import com.marchenkoteam.kotlinlearning.models.UserTest
import com.marchenkoteam.kotlinlearning.repositories.UserTestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserTestService @Autowired constructor(private val userTestRepository: UserTestRepository,
                                             private val testService: TestService) {

    fun save(userTestForm: UserTestForm) {
        userTestRepository.save(userTestForm.getUserTest())
    }

    fun initForNewUser(userId: Long) {
        val tests = testService.findAll()
        for (test in tests) {
            val userTestForm = UserTestForm(userId, test.id)
            save(userTestForm)
        }
    }

    fun changeStatus(status: UserTest.TestStatus, testId: Long, userId: Long) {
        val userTest = userTestRepository.getByTestIdAndUserId(testId, userId)
        userTest.status = status
        userTestRepository.save(userTest)
    }

    fun findByTestIdAndUserId(testId: Long, userId: Long): UserTest = userTestRepository.getByTestIdAndUserId(testId, userId)
}