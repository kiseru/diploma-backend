package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.Test
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface TestRepository : JpaRepository<Test, Long> {

    @Query(value = "SELECT tests FROM test t WHERE t.theme_id = ?1")
    fun findByThemeId(themeId: Long): Optional<List<Test>>
}