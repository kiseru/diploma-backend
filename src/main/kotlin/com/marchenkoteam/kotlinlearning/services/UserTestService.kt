package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.forms.UserTestForm
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
}