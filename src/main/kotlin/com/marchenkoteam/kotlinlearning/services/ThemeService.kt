package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.dto.TestDto
import com.marchenkoteam.kotlinlearning.dto.ThemeDto
import com.marchenkoteam.kotlinlearning.exceptions.BadRequestException
import com.marchenkoteam.kotlinlearning.forms.ThemeForm
import com.marchenkoteam.kotlinlearning.models.UserTest
import com.marchenkoteam.kotlinlearning.repositories.ThemeRepository
import com.marchenkoteam.kotlinlearning.repositories.UserTestRepository
import com.marchenkoteam.kotlinlearning.repositories.UserThemeRankRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ThemeService @Autowired constructor(private val themeRepository: ThemeRepository,
                                          private val userThemeRankRepository: UserThemeRankRepository,
                                          private val userTestRepository: UserTestRepository,
                                          private val authService: AuthService) {

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

    fun getTest(id: Long): TestDto {
        val user = authService.getMe()
        val theme = themeRepository.findById(id)
                .orElseThrow { BadRequestException("No such theme.") }
        val rank = userThemeRankRepository.findByUserId(user.id, id)
        var userTestsList = userTestRepository.getByUserId(user.id)
        userTestsList = userTestsList.filter { it.status == UserTest.TestStatus.UNDONE }
        val testList = userTestsList.map { it.test }
        val resultList = testList.filter { it.rank == rank }.filter { it.themeId == theme.id } as ArrayList
        return TestDto(resultList.random())
    }
}
