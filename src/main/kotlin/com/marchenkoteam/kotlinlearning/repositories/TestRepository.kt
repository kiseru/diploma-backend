package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.Test
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface TestRepository : JpaRepository<Test, Long> {

    @Query(value = "SELECT * FROM test WHERE theme_id = ?1", nativeQuery = true)
    fun findTestsByThemeId(themeId: Long): Optional<List<Test>>

}