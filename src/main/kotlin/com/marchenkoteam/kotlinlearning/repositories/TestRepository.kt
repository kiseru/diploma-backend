package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.Test
import org.springframework.data.mongodb.repository.MongoRepository

interface TestRepository : MongoRepository<Test, String> {
    fun findAllByThemeId(themeId: String): List<Test>
}