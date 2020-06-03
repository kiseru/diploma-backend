package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.UserTest
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface UserTestRepository : MongoRepository<UserTest, String> {
    fun findByUserIdAndTestId(userId: String, testId: String): Optional<UserTest>
}
