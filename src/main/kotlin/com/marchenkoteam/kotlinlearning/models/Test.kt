package com.marchenkoteam.kotlinlearning.models

import javax.persistence.*

@Entity
class Test(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long = 0,
           @ManyToOne @JoinColumn(nullable = false) var theme: Theme,
           @Column(nullable = false) var name: String = "",
           @Column(nullable = false) var description: String = "",
           @Column(nullable = false) var inputData: String = "",
           @Column(nullable = false) var outputData: String = "",
           @OneToMany(mappedBy = "test", cascade = [CascadeType.ALL]) var testSkill: Set<TestSkill> = HashSet())
