package com.marchenkoteam.kotlinlearning.models

import javax.persistence.*

@Entity
class TestSkill(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
                @Column(nullable = false) var name: String = "",
                @ManyToOne(cascade = [CascadeType.ALL]) @JoinColumn(nullable = false) var test: Test,
                @Column(nullable = false) var value: Int = 0)