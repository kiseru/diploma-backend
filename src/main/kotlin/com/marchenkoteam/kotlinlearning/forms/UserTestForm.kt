package com.marchenkoteam.kotlinlearning.forms

import com.fasterxml.jackson.annotation.JsonIgnore
import com.marchenkoteam.kotlinlearning.models.Test
import com.marchenkoteam.kotlinlearning.models.User
import com.marchenkoteam.kotlinlearning.models.UserTest

class UserTestForm(var userId: Long,
                   var testId: Long,
                   var code: String = "",
                   var status: UserTest.TestStatus = UserTest.TestStatus.UNDONE) {

    @JsonIgnore
    fun getUserTest() = UserTest(user = User(userId), test = Test(testId))
}