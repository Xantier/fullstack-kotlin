package com.packtpub.handler

import com.packtpub.HelloSayer
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono


class ApiHandler(private val helloSayer: HelloSayer) {

    fun hello(req: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .json()
            .body(
                Mono.just(
                    helloSayer.sayHello(req.queryParam("name").orElse("User"))
                )
            )

}