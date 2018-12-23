package com.marchenkoteam.kotlinlearning.dto

import com.marchenkoteam.kotlinlearning.models.Skill
import com.marchenkoteam.kotlinlearning.models.Test
import com.marchenkoteam.kotlinlearning.models.Theme
import com.marchenkoteam.kotlinlearning.models.ThemeSkill

data class ThemeDto(val id: Long, val name: String, val description: String, val tests: List<TestDto>,
                    val skills: List<ThemeSkillDto>) {
    constructor(theme: Theme) : this(theme.id, theme.name, theme.description, theme.tests.map(::TestDto),
                                     theme.themeSkills.map(::ThemeSkillDto))

    data class TestDto(val id: Long, val name: String, val description: String) {
        constructor(test: Test) : this(test.id, test.name, test.description)
    }

    data class ThemeSkillDto(val id: Long, val value: Int, val skill: SkillDto) {
        constructor(themeSkill: ThemeSkill) : this(themeSkill.id, themeSkill.value, SkillDto(themeSkill.skill))
    }

    class SkillDto(val id: Long, val name: String) {
        constructor(skill: Skill) : this(skill.id, skill.name)
    }
}