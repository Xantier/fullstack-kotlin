package com.packtpub.route

import com.packtpub.handler.ViewHandler
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router


class ViewRoutes(private val viewHandler: ViewHandler) {

    @Bean
    fun viewRouter(): RouterFunction<ServerResponse> =
        router {
            ("/projects" and accept(MediaType.TEXT_HTML)).nest {
                GET("/view", viewHandler::handle)
            }
            resources("/**", ClassPathResource("/static"))
        }
}