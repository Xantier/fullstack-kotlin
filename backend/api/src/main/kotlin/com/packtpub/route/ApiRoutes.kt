package com.packtpub.route

import com.packtpub.handler.ApiHandler
import com.packtpub.util.WithLogging
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.router


class ApiRoutes(private val apiHandler: ApiHandler) : WithLogging(){

    @Bean
    fun apiRouter() =
        router {
            (accept(MediaType.APPLICATION_JSON_UTF8) and "/api").nest {
                "/project".nest {
                    POST("/save", apiHandler::handle)
                }
            }
        }.filter { request, next ->
            LOG.debug(request)
            next.handle(request)
        }
}