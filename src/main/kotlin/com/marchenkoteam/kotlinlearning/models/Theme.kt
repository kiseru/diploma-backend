package com.marchenkoteam.kotlinlearning.models

import javax.persistence.*

@Entity
class Theme(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
            @Column(nullable = false, unique = true) var name: String = "",
            @Column(nullable = false) var description: String,
            @OneToMany(mappedBy = "theme", cascade = [CascadeType.ALL]) val tests: Set<Test> = HashSet(),
            @OneToMany(mappedBy = "theme", cascade = [CascadeType.ALL]) val themeSkills: Set<ThemeSkill> = HashSet())