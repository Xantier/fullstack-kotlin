package com.packtpub.route

import com.packtpub.ProjectService
import com.packtpub.util.htmlView
import com.packtpub.views.index
import org.springframework.context.annotation.Bean
import org.springframework.core.io.ClassPathResource
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono


class ViewRoutes(private val projectService: ProjectService) {

    @Bean
    fun viewRouter() =
        router {
            accept(MediaType.TEXT_HTML).nest {
                GET("/hello") { req ->
                    val name = req.queryParam("name").orElse("User")
                    ServerResponse.ok()
                        .htmlView(Mono.just(
                            index("Hello $name")
                        ))
                }
            }
            resources("/**", ClassPathResource("/static"))
        }
}