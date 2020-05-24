package com.marchenkoteam.kotlinlearning.dto

import com.marchenkoteam.kotlinlearning.models.Theme

data class ThemeDto(val id: String?, val name: String, val description: String, val tests: List<TestDto>) {
    companion object {
        fun from(theme: Theme, tests: List<TestDto>): ThemeDto = ThemeDto(theme.id,
                                                                          theme.name,
                                                                          theme.description,
                                                                          tests)

        fun from(theme: Theme): ThemeDto = from(theme, ArrayList())
    }
}