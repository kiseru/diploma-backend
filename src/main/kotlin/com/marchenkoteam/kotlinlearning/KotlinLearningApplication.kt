package com.marchenkoteam.kotlinlearning

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.CrossOrigin
import springfox.documentation.swagger2.annotations.EnableSwagger2

@SpringBootApplication
@EnableSwagger2
@CrossOrigin
class KotlinLearningApplication

fun main(args: Array<String>) {
    runApplication<KotlinLearningApplication>(*args)
}
