package com.marchenkoteam.kotlinlearning.models

import javax.persistence.*

@Entity
data class UserTheme(@Id
                     @GeneratedValue(strategy = GenerationType.IDENTITY)
                     var id: Long = 0,
                     @ManyToOne
                     @JoinColumn(name = "user_id", nullable = false)
                     var user: User,
                     @ManyToOne
                     @JoinColumn(name = "theme_id", nullable = false)
                     var theme: Theme,
                     @Column(nullable = false)
                     var rank: Int = 0)