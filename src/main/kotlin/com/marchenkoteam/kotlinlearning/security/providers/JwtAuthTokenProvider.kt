package com.marchenkoteam.kotlinlearning.security.providers

import com.marchenkoteam.kotlinlearning.models.User
import com.marchenkoteam.kotlinlearning.security.auth.JwtAuthToken
import com.marchenkoteam.kotlinlearning.security.details.UserDetailsImpl
import com.marchenkoteam.kotlinlearning.security.role.Role
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.AuthenticationServiceException
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component

@Component
class JwtAuthTokenProvider : AuthenticationProvider {

    @Value("\${app.jwtSecret}")
    private lateinit var jwtSecret: String

    override fun authenticate(authentication: Authentication): Authentication {
        val authToken = authentication as JwtAuthToken
        val body: Claims

        try {
            body = Jwts
                    .parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(authToken.name)
                    .body
        } catch (e: JwtException) {
            e.printStackTrace()
            throw AuthenticationServiceException("Invalid token.")
        }

        val userDetails = UserDetailsImpl(User().apply {
            id = body["sub"].toString().toLong()
            email = body["email"].toString()
            role = Role.valueOf(body["role"].toString())
        })

        authToken.details = userDetails
        authToken.isAuthenticated = true
        return authToken
    }

    override fun supports(auth: Class<*>): Boolean {
        return JwtAuthToken::class.java == auth
    }
}