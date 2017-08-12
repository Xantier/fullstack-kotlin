package com.packtpub

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController(private val helloSayer: HelloSayer) {
    @GetMapping
    fun sayHello(@RequestParam("name") name: String): String =
        helloSayer.sayHello(name)
}