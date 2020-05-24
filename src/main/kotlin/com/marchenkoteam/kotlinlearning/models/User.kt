package com.marchenkoteam.kotlinlearning.models

import com.marchenkoteam.kotlinlearning.security.role.Role
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document
class User(@Id var id: String? = null,
           var firstName: String = "",
           var lastName: String = "",
           @Indexed(unique = true) var email: String = "",
           var password: String = "",
           var role: Role = Role.USER)