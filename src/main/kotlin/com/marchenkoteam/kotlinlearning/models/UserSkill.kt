package com.marchenkoteam.kotlinlearning.models

import javax.persistence.*

@Entity
class UserSkill(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
                @Column var name: String = "",
                @Column(nullable = false) var value: Int = 0,
                @ManyToOne(cascade = [CascadeType.ALL]) @JoinColumn(nullable = false) var user: User)