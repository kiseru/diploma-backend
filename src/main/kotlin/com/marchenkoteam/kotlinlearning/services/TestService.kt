package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.dto.TestDto
import com.marchenkoteam.kotlinlearning.exceptions.BadRequestException
import com.marchenkoteam.kotlinlearning.forms.TestForm
import com.marchenkoteam.kotlinlearning.models.Test
import com.marchenkoteam.kotlinlearning.repositories.TestRepository
import com.marchenkoteam.kotlinlearning.repositories.ThemeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TestService @Autowired constructor(private val testRepository: TestRepository,
                                         private val themeRepository: ThemeRepository) {

    fun findById(id: String): TestDto {
        val test = testRepository.findById(id)
                .orElseThrow { BadRequestException("No such test.") }
        return TestDto.from(test)
    }

    fun findAll() = testRepository.findAll().map(TestDto.Companion::from)

    fun save(testForm: TestForm): TestDto? {
        var test = Test.from(testForm)
        test = testRepository.save(test)
        return TestDto.from(test)
    }

    fun deleteById(id: String) {
        testRepository.deleteById(id)
    }
}