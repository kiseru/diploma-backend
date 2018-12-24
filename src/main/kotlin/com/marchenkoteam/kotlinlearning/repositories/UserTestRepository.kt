package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.UserTest
import org.springframework.data.jpa.repository.JpaRepository

interface UserTestRepository : JpaRepository<UserTest, Long> {

    fun getByUserId(userId: Long) : List<UserTest>

    fun getByTestIdAndUserId(testId: Long, userId: Long) : UserTest
}