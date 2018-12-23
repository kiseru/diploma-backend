package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.TestSkill
import org.springframework.data.jpa.repository.JpaRepository

interface TestSkillRepository : JpaRepository<TestSkill, Long>