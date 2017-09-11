package com.packtpub.route

import com.packtpub.handler.LoginHandler
import com.packtpub.handler.ViewHandler
import com.packtpub.views.login
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router


class ViewRoutes(
    private val viewHandler: ViewHandler,
    private val loginHandler: LoginHandler) {

    @Bean
    fun viewRouter(): RouterFunction<ServerResponse> =
        router {
            ("/projects" and accept(MediaType.TEXT_HTML)).nest {
                GET("/view", viewHandler::handle)
            }
            ("/login" and accept(MediaType.TEXT_HTML)).nest {
                GET("/", loginHandler::getForm)
                POST("/", loginHandler::login)
            }
            resources("/**", ClassPathResource("/static"))
        }
}