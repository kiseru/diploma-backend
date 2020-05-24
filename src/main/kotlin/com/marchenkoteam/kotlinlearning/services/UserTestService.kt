package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.forms.UserTestForm
import com.marchenkoteam.kotlinlearning.models.UserTest
import com.marchenkoteam.kotlinlearning.repositories.TestRepository
import com.marchenkoteam.kotlinlearning.repositories.UserRepository
import com.marchenkoteam.kotlinlearning.repositories.UserTestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserTestService @Autowired constructor(private val userTestRepository: UserTestRepository,
                                             private val testRepository: TestRepository,
                                             private val userRepository: UserRepository) {

    fun findByTestIdAndUserId(testId: Long, userId: Long): UserTest? {
//        return userTestRepository.getByTestIdAndUserId(testId, userId)
        return null
    }

    fun create(userTestForm: UserTestForm): UserTest? {
//        val test = testRepository.getOne(userTestForm.test)
//        val user = userRepository.getOne(userTestForm.user)
//        val userTest = UserTest(user, test, userTestForm.code)
//        return userTestRepository.save(userTest)
        return null
    }
}