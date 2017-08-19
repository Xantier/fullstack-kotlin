package com.packtpub

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HelloWorldConfig {
    @Bean
    fun helloSayer(): HelloSayer = HelloWorld()
}