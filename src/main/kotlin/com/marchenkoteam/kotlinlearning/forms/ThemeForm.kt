package com.marchenkoteam.kotlinlearning.forms

import com.fasterxml.jackson.annotation.JsonIgnore
import com.marchenkoteam.kotlinlearning.models.Theme

data class ThemeForm(val id: Long, val name: String, val description: String, val skills: List<SkillForm>) {
    data class SkillForm(val name: String, val value: Int)

    @JsonIgnore
    fun getTheme() = Theme(id, name, description)
}