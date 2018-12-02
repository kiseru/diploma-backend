package com.marchenkoteam.kotlinlearning.models

import javax.persistence.*

@Entity
data class UserTest(@Id
                    @GeneratedValue(strategy = GenerationType.IDENTITY)
                    var id: Long,
                    @ManyToOne
                    @JoinColumn(name = "user_id", nullable = false)
                    var user: User,
                    @ManyToOne
                    @JoinColumn(name = "test_id", nullable = false)
                    var test: Test,
                    @Column(nullable = false)
                    var code: String)
