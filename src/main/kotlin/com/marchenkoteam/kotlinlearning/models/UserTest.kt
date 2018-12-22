package com.marchenkoteam.kotlinlearning.models

import java.util.*
import javax.persistence.*

@Entity
class UserTest(@Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
               @ManyToOne @JoinColumn(nullable = false) var user: User,
               @ManyToOne @JoinColumn(nullable = false) var test: Test,
               @Column(nullable = false) var code: String = "",
               @Column(nullable = false) var createdAt: Date = Date(),
               @Column(nullable = false) @Enumerated(EnumType.ORDINAL) var status: TestStatus = TestStatus.FAILED)
