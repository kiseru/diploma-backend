package com.marchenkoteam.kotlinlearning.controllers

import com.marchenkoteam.kotlinlearning.dto.TestDto
import com.marchenkoteam.kotlinlearning.models.Test
import com.marchenkoteam.kotlinlearning.services.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/tests")
class TestController @Autowired constructor(private val testService: TestService) {

    @GetMapping("{id}")
    fun getTest(@PathVariable("id") id: Long): TestDto = testService.findById(id)
}