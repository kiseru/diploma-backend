package com.marchenkoteam.kotlinlearning.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document


@Document
class Theme(@Id var id: String? = null, var name: String = "", var description: String = "")