package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.dto.ThemeDto
import com.marchenkoteam.kotlinlearning.exceptions.BadRequestException
import com.marchenkoteam.kotlinlearning.forms.ThemeForm
import com.marchenkoteam.kotlinlearning.forms.UserTestForm
import com.marchenkoteam.kotlinlearning.models.Theme
import com.marchenkoteam.kotlinlearning.repositories.ThemeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ThemeService @Autowired constructor(private val themeRepository: ThemeRepository,
                                          private val compilerService: CompilerService,
                                          private val userTestService: UserTestService) {

    fun findAll() = themeRepository.findAll()
            .map(::ThemeDto)

    fun findById(id: Long): Theme {
        return themeRepository.findById(id)
                .orElseThrow { BadRequestException("No such theme.") }
    }

    fun save(themeForm: ThemeForm): ThemeDto {
        val theme = themeRepository.save(themeForm.getTheme())
        return ThemeDto(theme)
    }

    fun deleteById(id: Long) = themeRepository.deleteById(id)

    fun sendTest(userTestForm: UserTestForm, themeId: Long) {
        val userTest = userTestService.create(userTestForm)
        compilerService.checkTest(userTest)
    }
}
