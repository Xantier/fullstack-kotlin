package com.packtpub

import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router


class ViewRoutes (private val viewHandler: ViewHandler){

    @Bean
    fun viewRouter() =
        router {
            accept(MediaType.TEXT_HTML).nest {
                GET("/hello", viewHandler::hello)
            }
            resources("/**", ClassPathResource("static/"))
        }
}