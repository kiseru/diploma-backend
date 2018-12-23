package com.marchenkoteam.kotlinlearning.dto

import com.marchenkoteam.kotlinlearning.models.Skill
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

    data class TestSkillDto(val id: Long, var value: Int, val skill: SkillDto) {
        constructor(testSkill: TestSkill) : this(testSkill.skill.id, testSkill.value, SkillDto(testSkill.skill))
    }

    class SkillDto(val id: Long, val name: String) {
        constructor(skill: Skill) : this(skill.id, skill.name)
    }
}