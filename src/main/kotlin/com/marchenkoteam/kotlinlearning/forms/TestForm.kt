package com.marchenkoteam.kotlinlearning.forms

import com.fasterxml.jackson.annotation.JsonIgnore
import com.marchenkoteam.kotlinlearning.models.Test

data class TestForm(val id: Long,
                    val rank: Int,
                    val name: String,
                    val themeId: Long,
                    val timeLimit: Long,
                    val description: String,
                    val inputData: String,
                    val outputData: String) {

    @JsonIgnore
    fun getTest() = Test(id, rank = rank, name = name, timeLimit = timeLimit, description = description,
            inputData = inputData, outputData = outputData, themeId = themeId)
}