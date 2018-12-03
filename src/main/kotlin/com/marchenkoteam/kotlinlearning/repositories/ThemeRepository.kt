package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.Test
import com.marchenkoteam.kotlinlearning.models.Theme
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface ThemeRepository : JpaRepository<Theme, Long> {

    @Query(value = "SELECT tests FROM test t WHERE t.theme_id = ?1")
    fun findTestsByThemeId(themeId: Long): Optional<List<Test>>
}