package com.marchenkoteam.kotlinlearning.models

import javax.persistence.*

@Entity
class Skill(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
            @Column(nullable = false, unique = true) var name: String = "",
            @OneToMany(mappedBy = "skill") var userSkills: Set<UserSkill> = HashSet(),
            @OneToMany(mappedBy = "skill") var testSkills: Set<TestSkill> = HashSet(),
            @OneToMany(mappedBy = "skill") var themeSkills: Set<ThemeSkill> = HashSet())