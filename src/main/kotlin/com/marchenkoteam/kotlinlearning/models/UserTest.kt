package com.marchenkoteam.kotlinlearning.models

import java.io.Serializable
import java.util.*
import javax.persistence.*

@Entity
@IdClass(UserTestId::class)
class UserTest(@Id @ManyToOne @JoinColumn(nullable = false) var user: User,
               @Id @ManyToOne @JoinColumn(nullable = false) var test: Test,
               @Column(nullable = false) var code: String = "",
               @Column(nullable = false) var createdAt: Date = Date(),
               @Column(nullable = false) @Enumerated(EnumType.ORDINAL) var status: TestStatus = TestStatus.FAILED)

private class UserTestId(var user: User,
                         var test: Test) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        if (other !is UserTestId) {
            return false
        }

        return user.id == other.user.id && test.id == other.test.id
    }

    override fun hashCode(): Int {
        return Objects.hash(user.id, test.id)
    }
}
