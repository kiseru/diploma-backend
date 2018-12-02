package com.marchenkoteam.kotlinlearning.security.config

import com.marchenkoteam.kotlinlearning.security.details.UserDetailsServiceImpl
import com.marchenkoteam.kotlinlearning.security.filters.JwtAuthTokenFilter
import com.marchenkoteam.kotlinlearning.security.providers.JwtAuthTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var jwtAuthTokenFilter: JwtAuthTokenFilter

    @Autowired
    private lateinit var jwtAuthTokenProvider: JwtAuthTokenProvider

    @Autowired
    private lateinit var userDetailsService: UserDetailsServiceImpl

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
                .authenticationProvider(jwtAuthTokenProvider)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .addFilterBefore(jwtAuthTokenFilter, BasicAuthenticationFilter::class.java)
    }
}