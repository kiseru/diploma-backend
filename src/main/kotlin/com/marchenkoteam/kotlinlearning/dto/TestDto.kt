package com.marchenkoteam.kotlinlearning.dto

import com.marchenkoteam.kotlinlearning.models.Test

data class TestDto(val id: String?, val name: String, val description: String) {
    companion object {
        fun from(test: Test) = TestDto(test.id, test.name, test.description)
    }
}