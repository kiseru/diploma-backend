package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.Skill
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface SkillRepository : JpaRepository<Skill, Long> {
    fun findByName(name: String): Optional<Skill>
}