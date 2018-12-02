package com.marchenkoteam.kotlinlearning.models

import com.marchenkoteam.kotlinlearning.security.role.Role
import javax.persistence.*

@Entity
@Table(name = "app_user")
data class User(@Id
                @GeneratedValue(strategy = GenerationType.IDENTITY)
                var id: Long = 0,
                var firstName: String = "",
                var lastName: String = "",
                @Column(unique = true, nullable = false)
                var email: String = "",
                @Column(nullable = false)
                var password: String = "",
                @Column(nullable = false)
                @Enumerated(EnumType.STRING)
                var role: Role = Role.USER,
                @OneToMany(mappedBy = "user")
                var userTest: Set<UserTest> = HashSet())
