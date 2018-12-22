package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.dto.ThemeDto
import com.marchenkoteam.kotlinlearning.exceptions.BadRequestException
import com.marchenkoteam.kotlinlearning.exceptions.NotFoundException
import com.marchenkoteam.kotlinlearning.forms.SkillForm
import com.marchenkoteam.kotlinlearning.forms.ThemeForm
import com.marchenkoteam.kotlinlearning.models.Skill
import com.marchenkoteam.kotlinlearning.models.ThemeSkill
import com.marchenkoteam.kotlinlearning.repositories.SkillRepository
import com.marchenkoteam.kotlinlearning.repositories.ThemeRepository
import com.marchenkoteam.kotlinlearning.repositories.ThemeSkillRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ThemeService @Autowired constructor(private val authService: AuthService,
                                          private val themeRepository: ThemeRepository,
                                          private val skillRepository: SkillRepository,
                                          private val themeSkillRepository: ThemeSkillRepository) {

    fun findAll() = themeRepository.findAll()
            .map(::ThemeDto)

    fun findById(id: Long): ThemeDto {
        val theme = themeRepository.findById(id)
                .orElseThrow { BadRequestException("No such theme.") }
        return ThemeDto(theme)
    }

    fun save(themeForm: ThemeForm): ThemeDto {
        val theme = themeRepository.save(themeForm.getTheme())
        return ThemeDto(theme)
    }

    fun deleteById(id: Long) = themeRepository.deleteById(id)

    fun addSkill(skillForm: SkillForm, themeId: Long): ThemeDto {
        val skill = skillRepository.findByName(skillForm.name)
                .orElseGet { skillRepository.save(Skill(name = skillForm.name)) }
        val theme = themeRepository.findById(themeId)
                .orElseThrow { NotFoundException("Theme not found.") }

        var themeSkill = ThemeSkill(value = skillForm.value, skill = skill, theme = theme)
        themeSkill = themeSkillRepository.save(themeSkill)
        return ThemeDto(themeSkill.theme)
    }

    fun updateSkill(skillForm: SkillForm, themeId: Long): ThemeDto {
        val skill = skillRepository.findByName(skillForm.name)
                .orElseThrow { NotFoundException("Skill not found.") }
        var themeSkill = skill.themeSkills.first { it.theme.id == themeId }
        themeSkill.value = skillForm.value
        themeSkill = themeSkillRepository.save(themeSkill)
        return ThemeDto(themeSkill.theme)
    }

    fun deleteSkill(skillForm: SkillForm, themeId: Long): ThemeDto {
        val skill = skillRepository.findByName(skillForm.name)
                .orElseThrow { NotFoundException("Skill not found") }
        val themeSkill = skill.themeSkills.first { it.theme.id == themeId }
        themeSkillRepository.delete(themeSkill)
        return findById(themeId)
    }
}
