package com.marchenkoteam.kotlinlearning.dto

import com.marchenkoteam.kotlinlearning.models.Test
import com.marchenkoteam.kotlinlearning.models.TestSkill
import com.marchenkoteam.kotlinlearning.models.Theme

data class TestDto(val id: Long, val name: String, val description: String, val theme: ThemeDto,
                   var skills: List<TestSkillDto>) {
    constructor(test: Test) : this(test.id, test.name, test.description, ThemeDto(test.theme),
                                   test.testSkill.map(::TestSkillDto))

    data class ThemeDto(val id: Long, val name: String, val description: String) {
        constructor(theme: Theme) : this(theme.id, theme.name, theme.description)
    }

    data class TestSkillDto(var name: String, var value: Int) {
        constructor(testSkill: TestSkill) : this(testSkill.name, testSkill.value)
    }
}