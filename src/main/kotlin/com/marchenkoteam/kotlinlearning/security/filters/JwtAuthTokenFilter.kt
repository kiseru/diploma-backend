package com.marchenkoteam.kotlinlearning.security.filters

import com.marchenkoteam.kotlinlearning.security.auth.JwtAuthToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class JwtAuthTokenFilter : Filter {
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val request = servletRequest as HttpServletRequest
        val token = request.getHeader("authToken")

        val authToken: JwtAuthToken

        if (token == null) {
            authToken = JwtAuthToken(null)
            authToken.isAuthenticated = false
        } else {
            authToken = JwtAuthToken(token)
            SecurityContextHolder.getContext().authentication = authToken
        }

        filterChain.doFilter(servletRequest, servletResponse)
    }
}