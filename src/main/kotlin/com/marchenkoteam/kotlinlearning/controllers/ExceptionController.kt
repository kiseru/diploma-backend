package com.marchenkoteam.kotlinlearning.controllers

import com.marchenkoteam.kotlinlearning.exceptions.AppException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController

@ControllerAdvice
@RestController
class ExceptionController {
    @ExceptionHandler(AppException::class)
    fun handleException(e: AppException) = ResponseEntity(e.message, e.httpStatus)
}