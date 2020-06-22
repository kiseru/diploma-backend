package com.marchenkoteam.kotlinlearning.controllers

import com.marchenkoteam.kotlinlearning.dto.TestDto
import com.marchenkoteam.kotlinlearning.forms.TestAnswer
import com.marchenkoteam.kotlinlearning.forms.TestForm
import com.marchenkoteam.kotlinlearning.services.TestService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
@RequestMapping("/tests")
class TestController @Autowired constructor(private val testService: TestService) {

    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    fun list(@RequestHeader authToken: String): List<TestDto> = testService.findAll()

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{id}")
    fun retrieve(@RequestHeader authToken: String, @PathVariable("id") id: String): TestDto = testService.findById(id)

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    fun create(@RequestHeader authToken: String, @RequestBody testForm: TestForm) = testService.save(testForm)

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    fun update(@RequestHeader authToken: String, @RequestBody testForm: TestForm,
               @PathVariable("id") id: Long) = testService.save(testForm)

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    fun delete(@RequestHeader authToken: String, @PathVariable("id") id: String) = testService.deleteById(id)

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/{id}/pass")
    fun passTest(@RequestHeader authToken: String,
                 @PathVariable("id") id: String,
                 @RequestBody testAnswer: TestAnswer) = testService.checkTest(id, testAnswer)
}