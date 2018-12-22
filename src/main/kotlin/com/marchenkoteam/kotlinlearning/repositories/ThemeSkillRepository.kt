package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.ThemeSkill
import org.springframework.data.jpa.repository.JpaRepository

interface ThemeSkillRepository : JpaRepository<ThemeSkill, Long>
