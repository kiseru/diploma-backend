package com.marchenkoteam.kotlinlearning.models

import com.marchenkoteam.kotlinlearning.forms.TestForm
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Test(@Id var id: String?,
           var name: String,
           var description: String,
           var inputData: String,
           var outputData: String,
           var themeId: String) {

    companion object {
        fun from(testForm: TestForm): Test {
            return Test(testForm.id,
                        testForm.name,
                        testForm.description,
                        testForm.inputData,
                        testForm.outputData,
                        testForm.themeId)
        }
    }
}