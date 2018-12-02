package com.marchenkoteam.kotlinlearning.dto

class TokenDto(var token: String,
               var expired: String) {
    constructor(token: String) : this(token, "")
}