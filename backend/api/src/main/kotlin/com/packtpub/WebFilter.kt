package com.packtpub

import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain


class PacktWebFilter(private val userService: UserService) : WebFilter {
    private val redirectDoneAttribute = "redirectDone"
    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain) =
/*        if (exchange.request.uri.path.startsWith("/login")) {
            exchange.formData
                .map { it.toSingleValueMap() }
                .flatMap { form ->
                    val name = form["name"]
                    val password = form["password"]
                    if (name != null) {
                        val packtUser = userService.getUserByName(name)
                        if (packtUser?.password == password!!) {
                            val token = UsernamePasswordAuthenticationToken(packtUser, packtUser, packtUser.roles.map(::SimpleGrantedAuthority).toList())
                            exchange.attributes["sessionId"] = "randomString"
                            exchange.session.flatMap {
                                it.changeSessionId().flatMap {
                                    "RandomGeneratedId".toMono()
                                }
                                it.attributes["sessionId"] = "adffdafda"
                                it.save()
                                chain.filter(exchange
                                    .mutate()
                                    .principal(token.toMono())
                                    .build())
                            }
                        } else {
                            Mono.empty<Void>()
                        }
                    } else {
                        Mono.empty<Void>()
                    }
                }
        } else {*/
            chain.filter(exchange)
        //}
}