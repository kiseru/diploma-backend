package com.marchenkoteam.kotlinlearning.security.details

import com.marchenkoteam.kotlinlearning.models.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class UserDetailsImpl(private val user: User) : UserDetails {
    private val grantedAuthority: GrantedAuthority = SimpleGrantedAuthority(user.role.toString())

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.singletonList(grantedAuthority)
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return user.email
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return user.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}