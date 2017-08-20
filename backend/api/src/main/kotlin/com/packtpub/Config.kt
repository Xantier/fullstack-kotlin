package com.packtpub

import com.packtpub.handler.ApiHandler
import com.packtpub.handler.ViewHandler
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
            bean { ApiRoutes(it.ref()) }
            bean { ApiHandler(it.ref()) }
            bean { ViewRoutes(it.ref()) }
            bean { ViewHandler(it.ref()) }
        }(ctx)
    })
    application.run(*args)
}