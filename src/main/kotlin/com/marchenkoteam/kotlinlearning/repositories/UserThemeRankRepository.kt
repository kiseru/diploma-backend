package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.UserTheme
import org.springframework.data.jpa.repository.JpaRepository

interface UserThemeRankRepository : JpaRepository<UserTheme, Long>