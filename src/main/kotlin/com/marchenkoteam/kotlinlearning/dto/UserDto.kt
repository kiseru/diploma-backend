package com.marchenkoteam.kotlinlearning.dto

import com.marchenkoteam.kotlinlearning.models.User

data class UserDto(var id: Long,
                   var firstName: String,
                   var lastName: String,
                   var email: String,
                   var role: String) {

    constructor(user: User) : this(user.id, user.firstName, user.lastName, user.email, user.role.toString())
}
