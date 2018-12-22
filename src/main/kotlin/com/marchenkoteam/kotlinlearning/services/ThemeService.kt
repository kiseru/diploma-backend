package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.dto.ThemeDto
import com.marchenkoteam.kotlinlearning.exceptions.BadRequestException
import com.marchenkoteam.kotlinlearning.exceptions.NotFoundException
import com.marchenkoteam.kotlinlearning.forms.ThemeForm
import com.marchenkoteam.kotlinlearning.models.ThemeSkill
import com.marchenkoteam.kotlinlearning.repositories.ThemeRepository
import com.marchenkoteam.kotlinlearning.repositories.ThemeSkillRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ThemeService @Autowired constructor(private val themeSkillRepository: ThemeSkillRepository,
                                          private val themeRepository: ThemeRepository) {

    fun findAll() = themeRepository.findAll()
            .map(::ThemeDto)

    fun findById(id: Long): ThemeDto {
        val theme = themeRepository.findById(id)
                .orElseThrow { BadRequestException("No such theme.") }
        return ThemeDto(theme)
    }

    fun save(themeForm: ThemeForm): ThemeDto {
        val theme = themeRepository.save(themeForm.getTheme())

        themeSkillRepository.findAll()
                .filter { it.theme.id == theme.id }
                .forEach(themeSkillRepository::delete)

        themeForm.skills
                .forEach { themeSkillRepository.save(ThemeSkill(name = it.name, value = it.value, theme = theme)) }
        return ThemeDto(themeRepository.findById(theme.id).orElseThrow { NotFoundException("Theme not found.") })
    }

    fun deleteById(id: Long) = themeRepository.deleteById(id)
}
