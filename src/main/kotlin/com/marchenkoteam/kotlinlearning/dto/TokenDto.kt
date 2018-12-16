package com.marchenkoteam.kotlinlearning.dto

class TokenDto(var token: String,
               var role: String,
               var expired: String) {
    constructor(token: String, role: String) : this(token, role, "")
}