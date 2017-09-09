package com.packtpub.handler

import com.fasterxml.jackson.core.JsonParseException
import com.packtpub.Validatable
import com.packtpub.util.WithLogging
import org.springframework.core.codec.DecodingException
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.HandlerStrategies
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebExceptionHandler
import reactor.core.publisher.Mono


class ExceptionHandler : WebExceptionHandler, WithLogging() {
    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        LOG.error("failed to handle request", ex)
        return handle(ex)
            .flatMap {
                it.populateBody(exchange)
            }
            .flatMap {
                Mono.empty<Void>()
            }
    }

    private fun handle(throwable: Throwable):
        Mono<ServerResponse> = when (throwable) {
        is JsonParseException,
        is DecodingException ->
            respond(HttpStatus.BAD_REQUEST,
                Validatable(genericError = throwable.localizedMessage))
        else                 ->
            respond(HttpStatus.INTERNAL_SERVER_ERROR,
                Validatable(genericError = throwable.localizedMessage))
    }

    private fun respond(httpStatus: HttpStatus, body: Any?):
        Mono<ServerResponse> =
        ServerResponse
            .status(httpStatus)
            .syncBody(body)
}

fun ServerResponse.populateBody(exchange: ServerWebExchange): Mono<Void> = writeTo(exchange, HandlerStrategiesResponseContext(HandlerStrategies.withDefaults()))
private class HandlerStrategiesResponseContext(val strategies: HandlerStrategies) : ServerResponse.Context {
    override fun messageWriters() = strategies.messageWriters()
    override fun viewResolvers() = strategies.viewResolvers()
}