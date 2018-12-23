package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.UserSkill
import org.springframework.data.jpa.repository.JpaRepository

interface UserSkillRepository : JpaRepository<UserSkill, Long>