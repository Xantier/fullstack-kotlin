package com.packtpub.route

import com.packtpub.handler.ApiHandler
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router


class ApiRoutes(private val apiHandler: ApiHandler) {

    @Bean
    fun apiRouter() =
        router {
            accept(MediaType.APPLICATION_JSON_UTF8).nest {
                GET("/apihello", apiHandler::hello)
            }
        }
}