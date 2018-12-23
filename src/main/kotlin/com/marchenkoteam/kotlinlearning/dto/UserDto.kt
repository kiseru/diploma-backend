package com.marchenkoteam.kotlinlearning.dto

import com.marchenkoteam.kotlinlearning.models.Skill
import com.marchenkoteam.kotlinlearning.models.User
import com.marchenkoteam.kotlinlearning.models.UserSkill

data class UserDto(val id: Long, val firstName: String, val lastName: String, val email: String, val role: String,
                   val skills: List<UserSkillDto>) {
    constructor(user: User) : this(user.id, user.firstName, user.lastName, user.email, user.role.toString(),
                                   user.userSkills.map(::UserSkillDto))

    data class UserSkillDto(val id: Long, val value: Int, val skill: SkillDto) {
        constructor(userSkill: UserSkill) : this(userSkill.id, userSkill.value, SkillDto(userSkill.skill))
    }

    class SkillDto(val id: Long, val name: String) {
        constructor(skill: Skill) : this(skill.id, skill.name)
    }
}
