package com.marchenkoteam.kotlinlearning.controllers

import com.marchenkoteam.kotlinlearning.forms.LoginForm
import com.marchenkoteam.kotlinlearning.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/login")
class LoginController @Autowired constructor(private val userService: UserService) {

    @PostMapping
    fun login(@RequestBody loginForm: LoginForm) = userService.login(loginForm)
}