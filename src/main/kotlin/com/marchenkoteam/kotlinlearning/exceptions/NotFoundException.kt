package com.marchenkoteam.kotlinlearning.exceptions

import org.springframework.http.HttpStatus

class NotFoundException(message: String) : AppException(HttpStatus.NOT_FOUND, message)
