package com.marchenkoteam.kotlinlearning.exceptions

import org.springframework.http.HttpStatus

open class AppException(val httpStatus: HttpStatus,
                        message: String) : RuntimeException(message)