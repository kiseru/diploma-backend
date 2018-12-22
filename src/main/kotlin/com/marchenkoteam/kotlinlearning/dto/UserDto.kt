package com.marchenkoteam.kotlinlearning.dto

import com.marchenkoteam.kotlinlearning.models.User
import com.marchenkoteam.kotlinlearning.models.UserSkill

data class UserDto(val id: Long, val firstName: String, val lastName: String, val email: String, val role: String,
                   val skills: List<UserSkillDto>) {
    constructor(user: User) : this(user.id, user.firstName, user.lastName, user.email, user.role.toString(),
                                   user.userSkills.map(::UserSkillDto))

    data class UserSkillDto(val name: String, val value: Int) {
        constructor(userSkill: UserSkill) : this(userSkill.name, userSkill.value)
    }
}
