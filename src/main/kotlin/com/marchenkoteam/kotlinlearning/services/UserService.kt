package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.dto.TokenDto
import com.marchenkoteam.kotlinlearning.exceptions.InvalidLoginOrPasswordException
import com.marchenkoteam.kotlinlearning.exceptions.PasswordsNotMatchedException
import com.marchenkoteam.kotlinlearning.forms.LoginForm
import com.marchenkoteam.kotlinlearning.forms.RegistrationForm
import com.marchenkoteam.kotlinlearning.models.User
import com.marchenkoteam.kotlinlearning.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(private val passwordEncoder: PasswordEncoder,
                                         private val tokenService: TokenService,
                                         private val userRepository: UserRepository) {

    fun save(registrationForm: RegistrationForm): TokenDto {
        if (registrationForm.password != registrationForm.passwordConfirm)
            throw PasswordsNotMatchedException()

        val user = User().apply {
            firstName = registrationForm.firstName
            lastName = registrationForm.lastName
            email = registrationForm.email
            password = passwordEncoder.encode(registrationForm.password)
        }
        return save(user)
    }

    private fun save(user: User): TokenDto {
        userRepository.save(user)
        return tokenService.getToken(user)
    }

    fun login(loginForm: LoginForm): TokenDto {
        val user = userRepository.findByEmail(loginForm.email)
                .orElseThrow { InvalidLoginOrPasswordException() }

        if (!passwordEncoder.matches(loginForm.password, user.password))
            throw InvalidLoginOrPasswordException()

        return tokenService.getToken(user)
    }
}