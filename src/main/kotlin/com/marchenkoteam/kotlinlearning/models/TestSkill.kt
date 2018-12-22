package com.marchenkoteam.kotlinlearning.models

import javax.persistence.*

@Entity
class TestSkill(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
                @ManyToOne @JoinColumn(nullable = false) var skill: Skill,
                @ManyToOne @JoinColumn(nullable = false) var test: Test,
                @Column(nullable = false) var value: Int = 0)