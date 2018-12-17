package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.UserTest
import com.marchenkoteam.kotlinlearning.models.UserTheme
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserTestRepository : JpaRepository<UserTest, Long> {

//    @Query(value = "SELECT test_id FROM user_test WHERE user_id = ?1 AND status = 2", nativeQuery = true)
    fun getByUserId(userId: Long) : List<UserTest>
}