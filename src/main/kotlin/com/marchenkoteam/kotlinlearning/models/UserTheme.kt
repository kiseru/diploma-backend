package com.marchenkoteam.kotlinlearning.models

import javax.persistence.*

@Entity
class UserTheme(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
                @ManyToOne @JoinColumn(nullable = false) var user: User,
                @ManyToOne @JoinColumn(nullable = false) var theme: Theme,
                @Column(nullable = false) @Enumerated(EnumType.ORDINAL) var status: ThemeStatus = ThemeStatus.STARTED)
