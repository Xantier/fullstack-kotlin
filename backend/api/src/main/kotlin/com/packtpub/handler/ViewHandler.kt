package com.packtpub.handler

import com.packtpub.HelloSayer
import com.packtpub.view.index
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono


class ViewHandler(private val helloSayer: HelloSayer) {


    fun hello(req: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok()
            .htmlView(
                index(helloSayer.sayHello(req.queryParam("name").orElse("User")))
            )
}