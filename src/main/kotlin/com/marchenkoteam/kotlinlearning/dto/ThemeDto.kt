package com.marchenkoteam.kotlinlearning.dto

import com.marchenkoteam.kotlinlearning.models.Test
import com.marchenkoteam.kotlinlearning.models.Theme

data class ThemeDto(val id: Long,
                    val name: String,
                    val description: String,
                    val tests: List<TestDto>) {

    constructor(theme: Theme) : this(theme.id, theme.name, theme.description, theme.tests.map(::TestDto))

    data class TestDto(val id: Long,
                       val name: String,
                       val description: String,
                       val rank: Int,
                       val timeLimit: Long) {

        internal constructor(test: Test) : this(test.id, test.name, test.description, test.rank, test.timeLimit)
    }
}