package com.marchenkoteam.kotlinlearning.models

import javax.persistence.*

@Entity
data class Theme(@Id
                 @GeneratedValue(strategy = GenerationType.IDENTITY)
                 var id: Long = 0,
                 @Column(nullable = false, unique = true)
                 var name: String = "",
                 @Column(nullable = false)
                 var description: String = "",
                 @OneToMany
                 @JoinColumn(name = "theme_id")
                 val tests: Set<Test> = HashSet())