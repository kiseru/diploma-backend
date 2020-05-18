package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.dto.TestDto
import com.marchenkoteam.kotlinlearning.exceptions.BadRequestException
import com.marchenkoteam.kotlinlearning.exceptions.NotFoundException
import com.marchenkoteam.kotlinlearning.forms.TestForm
import com.marchenkoteam.kotlinlearning.models.Test
import com.marchenkoteam.kotlinlearning.repositories.TestRepository
import com.marchenkoteam.kotlinlearning.repositories.ThemeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TestService @Autowired constructor(private val testRepository: TestRepository,
                                         private val themeRepository: ThemeRepository) {

    fun findById(id: Long): TestDto {
        val test = testRepository.findById(id)
                .orElseThrow { BadRequestException("No such test.") }
        return TestDto(test)
    }

    fun findAll() = testRepository.findAll().map(::TestDto)

    fun save(testForm: TestForm): TestDto {
        val theme = themeRepository.findById(testForm.theme)
                .orElseThrow { NotFoundException("Theme not found.") }
        var test = Test(id = testForm.id, theme = theme, name = testForm.name, description = testForm.description,
                inputData = testForm.inputData, outputData = testForm.outputData)
        test = testRepository.save(test)
        return TestDto(test)
    }

    fun deleteById(id: Long) {
        testRepository.deleteById(id)
    }
}