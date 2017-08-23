package com.packtpub

import com.packtpub.route.ApiRoutes
import com.packtpub.route.ViewRoutes
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans


@SpringBootApplication
class Config

fun main(args: Array<String>){
    val application = SpringApplication(Config::class.java)
    application.addInitializers(ApplicationContextInitializer<GenericApplicationContext> { ctx ->
        beans{
            bean { ViewRoutes(it.ref()) }
            bean { ApiRoutes(it.ref()) }
        }(ctx)
    })
    application.run(*args)
}