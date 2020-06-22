package com.marchenkoteam.kotlinlearning.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class UnitTest(@Id var id: String?,
               var inputData: String?,
               var outputData: String,
               var testId: String)