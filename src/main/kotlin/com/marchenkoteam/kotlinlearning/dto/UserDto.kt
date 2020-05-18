package com.marchenkoteam.kotlinlearning.dto

import com.marchenkoteam.kotlinlearning.models.User

data class UserDto(val id: Long, val firstName: String, val lastName: String, val email: String, val role: String) {
    constructor(user: User) : this(user.id, user.firstName, user.lastName, user.email, user.role.toString())
}
