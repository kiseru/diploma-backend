package com.marchenkoteam.kotlinlearning.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document

@Document
@CompoundIndexes(
        CompoundIndex(name = "user_test", def = "{'userId': 1, 'testId': 1}", unique = true)
)
class UserTest(@Id var id: String?, var userId: String, var testId: String, var testStatus: TestStatus)