package com.marchenkoteam.kotlinlearning.dto

import com.marchenkoteam.kotlinlearning.models.Test
import com.marchenkoteam.kotlinlearning.models.Theme

data class TestDto(val id: Long, val name: String, val description: String, val theme: ThemeDto) {
    constructor(test: Test) : this(test.id, test.name, test.description, ThemeDto(test.theme))

    data class ThemeDto(val id: Long, val name: String, val description: String) {
        constructor(theme: Theme) : this(theme.id, theme.name, theme.description)
    }
}