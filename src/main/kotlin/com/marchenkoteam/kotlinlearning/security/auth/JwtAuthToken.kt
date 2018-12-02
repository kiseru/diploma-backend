package com.marchenkoteam.kotlinlearning.security.auth

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JwtAuthToken(private val token: String?) : Authentication {

    lateinit var details: UserDetails
    private var isAuthenticated = false

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = details.authorities

    override fun setAuthenticated(isAuthenticated: Boolean) {
        this.isAuthenticated = isAuthenticated
    }

    override fun getName() = token

    override fun getCredentials(): Any? = null

    override fun getPrincipal(): Any? = null

    override fun isAuthenticated() = isAuthenticated

    override fun getDetails(): Any = details
}