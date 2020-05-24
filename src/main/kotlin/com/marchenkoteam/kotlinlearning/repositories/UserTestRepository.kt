package com.marchenkoteam.kotlinlearning.repositories

import com.marchenkoteam.kotlinlearning.models.UserTest
import org.springframework.data.mongodb.repository.MongoRepository

interface UserTestRepository : MongoRepository<UserTest, String>