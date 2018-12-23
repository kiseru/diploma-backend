package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.dto.TestDto
import com.marchenkoteam.kotlinlearning.exceptions.BadRequestException
import com.marchenkoteam.kotlinlearning.exceptions.NotFoundException
import com.marchenkoteam.kotlinlearning.forms.SkillForm
import com.marchenkoteam.kotlinlearning.forms.TestForm
import com.marchenkoteam.kotlinlearning.models.Skill
import com.marchenkoteam.kotlinlearning.models.Test
import com.marchenkoteam.kotlinlearning.models.TestSkill
import com.marchenkoteam.kotlinlearning.repositories.SkillRepository
import com.marchenkoteam.kotlinlearning.repositories.TestRepository
import com.marchenkoteam.kotlinlearning.repositories.TestSkillRepository
import com.marchenkoteam.kotlinlearning.repositories.ThemeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TestService @Autowired constructor(private val skillRepository: SkillRepository,
                                         private val testSkillRepository: TestSkillRepository,
                                         private val testRepository: TestRepository,
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

    fun addSkill(skillForm: SkillForm, testId: Long): TestDto {
        val skill = skillRepository.findByName(skillForm.name)
                .orElseGet { skillRepository.save(Skill(name = skillForm.name)) }
        val test = testRepository.findById(testId)
                .orElseThrow { NotFoundException("Test not found.") }

        var testSkill = TestSkill(skill = skill, test = test, value = skillForm.value)
        testSkill = testSkillRepository.save(testSkill)
        return TestDto(testSkill.test)
    }

    fun updateSkill(skillForm: SkillForm, testId: Long): TestDto {
        val skill = skillRepository.findByName(skillForm.name)
                .orElseThrow { NotFoundException("Skill not found.") }
        var testSkill = skill.testSkills.first { it.test.id == testId }
        testSkill.value = skillForm.value
        testSkill = testSkillRepository.save(testSkill)
        return TestDto(testSkill.test)
    }

    fun deleteSkill(skillForm: SkillForm, testId: Long): TestDto {
        val skill = skillRepository.findByName(skillForm.name)
                .orElseThrow { NotFoundException("Skill not found.") }
        val testSkill = skill.testSkills.first { it.test.id == testId }
        testSkillRepository.delete(testSkill)
        return findById(testId)
    }
}