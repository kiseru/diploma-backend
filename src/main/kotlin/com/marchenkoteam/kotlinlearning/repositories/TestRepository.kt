package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.Test
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface TestRepository : JpaRepository<Test, Long> {

}