package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.UserTheme
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserThemeRankRepository : JpaRepository<UserTheme, Long> {
    @Query(value = "SELECT rank FROM user_theme WHERE user_id = ?1 AND theme_id = ?2", nativeQuery = true)
    fun findByUserId(userId: Long, themeId: Long): Int?
}