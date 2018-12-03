package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.dto.TestDto
import com.marchenkoteam.kotlinlearning.exceptions.BadRequestException
import com.marchenkoteam.kotlinlearning.forms.TestForm
import com.marchenkoteam.kotlinlearning.repositories.TestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TestService @Autowired constructor(private val testRepository: TestRepository) {

    fun findById(id: Long): com.marchenkoteam.kotlinlearning.dto.TestDto {
        val test = testRepository.findById(id)
                .orElseThrow { BadRequestException("No such test.") }
        return TestDto(test)
    }

    fun findByThemeId(themeId: Long): List<TestDto> {
        val testList = testRepository.findByThemeId(themeId)
                .orElseThrow { BadRequestException("No tests found for that theme.") }
        return testList.map { TestDto(it) }
    }

    fun save(testForm: TestForm) {
        testRepository.save(testForm.getTest())
    }

    fun deleteById(id: Long) = testRepository.deleteById(id)
}