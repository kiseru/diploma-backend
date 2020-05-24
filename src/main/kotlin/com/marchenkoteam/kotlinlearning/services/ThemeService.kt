package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.dto.TestDto
import com.marchenkoteam.kotlinlearning.dto.ThemeDto
import com.marchenkoteam.kotlinlearning.exceptions.BadRequestException
import com.marchenkoteam.kotlinlearning.forms.ThemeForm
import com.marchenkoteam.kotlinlearning.forms.UserTestForm
import com.marchenkoteam.kotlinlearning.repositories.TestRepository
import com.marchenkoteam.kotlinlearning.repositories.ThemeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ThemeService @Autowired constructor(private val testRepository: TestRepository,
                                          private val themeRepository: ThemeRepository,
                                          private val compilerService: CompilerService,
                                          private val userTestService: UserTestService) {

    fun findAll() = themeRepository.findAll()
            .map(ThemeDto.Companion::from)

    fun findById(id: String): ThemeDto {
        val theme = themeRepository.findById(id)
                .orElseThrow { BadRequestException("No such theme.") }
        val themeTests = testRepository.findAllByThemeId(id)
        return ThemeDto.from(theme, themeTests.map(TestDto.Companion::from))
    }

    fun save(themeForm: ThemeForm): ThemeDto {
        val theme = themeRepository.save(themeForm.getTheme())
        return ThemeDto.from(theme)
    }

    fun deleteById(id: String) = themeRepository.deleteById(id)

    fun sendTest(userTestForm: UserTestForm, themeId: Long) {
//        val userTest = userTestService.create(userTestForm) ?: throw IllegalArgumentException()
//        compilerService.checkTest(userTest)
    }
}
