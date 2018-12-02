package com.marchenkoteam.kotlinlearning.services

import com.marchenkoteam.kotlinlearning.dto.TokenDto
import com.marchenkoteam.kotlinlearning.models.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class TokenService {

    @Value("\${app.jwtSecret}")
    private lateinit var jwtSecret: String

    fun getToken(user: User): TokenDto {
        val jwtToken = Jwts.builder()
                .claim("role", user.role.toString())
                .claim("email", user.email)
                .setSubject(user.id.toString())
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact()

        return TokenDto(jwtToken)
    }
}