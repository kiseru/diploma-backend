package com.marchenkoteam.kotlinlearning.dto

import com.marchenkoteam.kotlinlearning.models.Test
import com.marchenkoteam.kotlinlearning.models.Theme

data class TestDto(val id: Long,
                   val name: String,
                   val description: String,
                   val rank: Int,
                   val timeLimit: Long) {

    constructor(test: Test) : this(test.id, test.name, test.description, test.rank, test.timeLimit)
}