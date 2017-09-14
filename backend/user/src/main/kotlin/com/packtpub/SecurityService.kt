package com.packtpub

import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.server.authorization.AuthorizationContext
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono


internal class SecurityService(private val userService: UserService) {

    fun isAdmin(authentication: Mono<Authentication>, context: AuthorizationContext): Mono<AuthorizationDecision> {
        val auth: Authentication? = SecurityContextHolder.getContext().authentication
        return context.exchange.session.flatMap {
            println(context.exchange.request.cookies["session"])
            println(context.exchange.attributes["sessionId"])
            println(it.id)
            println(it.attributes)
            println(it.getAttribute<String>("sessionId"))
            println(it.attributes["sessionId"])
            AuthorizationDecision(false).toMono()
        }

    }
}