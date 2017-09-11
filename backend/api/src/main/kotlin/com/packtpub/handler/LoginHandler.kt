package com.packtpub.handler

import com.packtpub.UserService
import com.packtpub.util.htmlView
import com.packtpub.views.login
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono


class LoginHandler(private val userService: UserService) {
    fun getForm(req: ServerRequest) =
        ServerResponse.ok().htmlView(
            Mono.just(login())
        )

    fun login(req: ServerRequest) =
        req.body(BodyExtractors.toFormData())
            .map { it.toSingleValueMap() }
            .map { form ->
                val name = form["name"]
                val password = form["password"]
                println(name)
                println(password)
                name
            }.flatMap {
            ServerResponse.ok().body(
                Mono.just(login())
            )
        }

}