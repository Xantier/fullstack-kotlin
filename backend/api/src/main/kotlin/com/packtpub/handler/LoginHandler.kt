package com.packtpub.handler

import com.packtpub.UserService
import com.packtpub.util.htmlView
import com.packtpub.views.login
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.reactive.function.BodyExtractors
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono


class LoginHandler(private val userService: UserService,
                   private val viewHandler: ViewHandler) {
    fun getForm(req: ServerRequest) =
        ServerResponse.ok().htmlView(
            Mono.just(login())
        )

    fun login(req: ServerRequest) =
        req.body(BodyExtractors.toFormData())
            .map { it.toSingleValueMap() }
            .flatMap { form ->
                val name = form["name"]
                val password = form["password"]
                if (name != null) {
                    val packtUser = userService.getUserByName(name)
                    if (packtUser?.password == password!!) {
                        SecurityContextHolder.getContext().authentication =
                            UsernamePasswordAuthenticationToken(packtUser, packtUser, packtUser.roles.map(::SimpleGrantedAuthority).toList())
                        req.session().flatMap { session ->
                            session.attributes["username"] = packtUser.username
                            session.attributes["sessionId"] = "adffdafda"
                            session.save()
                            viewHandler.handle(req)
                        }
                    } else {
                        ServerResponse.status(HttpStatus.FORBIDDEN).build()
                    }
                } else {
                    ServerResponse.status(HttpStatus.FORBIDDEN).build()
                }

            }
}