package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.UnitTest
import org.springframework.data.mongodb.repository.MongoRepository

interface UnitTestRepository : MongoRepository<UnitTest, String> {
    fun findAllByTestId(testId: String): List<UnitTest>
}