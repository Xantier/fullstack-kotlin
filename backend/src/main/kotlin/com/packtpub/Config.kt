package com.packtpub

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
open class Config(
    private val helloWorld: HelloWorld)
    : CommandLineRunner {
    override fun run(vararg args: String?) {
        helloWorld.sayHello()
    }
}

fun main(args: Array<String>){
    SpringApplication.run(Config::class.java, *args)
}