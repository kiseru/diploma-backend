package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.Test
import org.springframework.data.jpa.repository.JpaRepository

interface TestRepository : JpaRepository<Test, Long>