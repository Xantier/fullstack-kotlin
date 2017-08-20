package com.packtpub.handler

import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono


fun ServerResponse.BodyBuilder.json() =
    contentType(MediaType.APPLICATION_JSON_UTF8)


fun ServerResponse.BodyBuilder.htmlView(view: String) =
    contentType(MediaType.TEXT_HTML).body(Mono.just(view))