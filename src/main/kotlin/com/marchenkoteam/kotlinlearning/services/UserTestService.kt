package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.models.UserTest
import com.marchenkoteam.kotlinlearning.repositories.UserTestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserTestService @Autowired constructor(private val userTestRepository: UserTestRepository) {

    fun findByTestIdAndUserId(testId: Long, userId: Long): UserTest = userTestRepository.getByTestIdAndUserId(testId, userId)
}