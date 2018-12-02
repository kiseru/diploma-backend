package com.marchenkoteam.kotlinlearning.exceptions

import org.springframework.http.HttpStatus

open class BadRequestException(message: String) : AppException(HttpStatus.BAD_REQUEST, message)
