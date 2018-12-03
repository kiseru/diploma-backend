package com.marchenkoteam.kotlinlearning.forms

import com.fasterxml.jackson.annotation.JsonIgnore
import com.marchenkoteam.kotlinlearning.models.Test
import com.marchenkoteam.kotlinlearning.models.Theme

data class TestForm(val id: Long,
                    val rank: Int,
                    val name: String,
                    val themeId: Long,
                    val timeLimit: Long,
                    val description: String,
                    val inputFilePath: String,
                    val outputFilePath: String) {

    @JsonIgnore
    fun getTest() = Test(id, rank = rank, name = name, timeLimit = timeLimit, description = description,
            inputFilePath = inputFilePath, outputFilePath = outputFilePath, theme = Theme(themeId))
}