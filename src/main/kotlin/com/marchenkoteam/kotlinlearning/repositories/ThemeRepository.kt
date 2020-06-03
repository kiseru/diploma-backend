package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.Theme
import org.springframework.data.mongodb.repository.MongoRepository

interface ThemeRepository : MongoRepository<Theme, String>