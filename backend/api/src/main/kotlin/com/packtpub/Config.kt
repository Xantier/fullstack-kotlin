package com.packtpub

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans


@SpringBootApplication
class Config(
    private val helloWorld: HelloWorld)
    : CommandLineRunner {
    override fun run(vararg args: String?) {
        helloWorld.sayHello()
    }
}

fun main(args: Array<String>){
    val application = SpringApplication(Config::class.java)
    application.addInitializers(ApplicationContextInitializer<GenericApplicationContext> { ctx ->
        beans{
            bean<HelloWorldConfig>()
        }(ctx)
    })
    application.run(*args)
}