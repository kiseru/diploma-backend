package com.marchenkoteam.kotlinlearning.models

import com.marchenkoteam.kotlinlearning.security.role.Role
import javax.persistence.*

@Entity
@Table(name = "app_user")
class User(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
           var firstName: String = "",
           var lastName: String = "",
           @Column(nullable = false, unique = true) var email: String = "",
           @Column(nullable = false) var password: String = "",
           @Column(nullable = false) @Enumerated(EnumType.STRING) var role: Role = Role.USER,
           @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL]) var userSkills: Set<UserSkill> = HashSet(),
           @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL]) val userThemes: Set<UserTheme> = HashSet())
