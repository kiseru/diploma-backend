package com.marchenkoteam.kotlinlearning.forms

data class TestForm(val id: Long, val name: String, val description: String, val theme: Long, val inputData: String,
                    val outputData: String, val skills: List<SkillDto>) {
    data class SkillDto(val name: String, val value: Int)
}