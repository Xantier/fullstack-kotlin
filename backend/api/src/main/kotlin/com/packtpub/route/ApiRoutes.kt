package com.packtpub.route

import com.packtpub.handler.ApiHandler
import com.packtpub.util.WithLogging
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router


class ApiRoutes(private val apiHandler: ApiHandler) : WithLogging() {

    @Bean
    fun apiRouter(): RouterFunction<ServerResponse> =
        router {
            ("/api" and accept(MediaType.APPLICATION_JSON_UTF8)).nest {
                "/projects".nest {
                    POST("/", apiHandler::handle)
                }
            }
        }.filter { request, next ->
            LOG.debug(request)
            next.handle(request)
        }
}