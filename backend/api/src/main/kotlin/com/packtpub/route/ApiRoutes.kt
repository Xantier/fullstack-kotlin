package com.packtpub.route

import com.packtpub.FieldErrorDTO
import com.packtpub.ProjectDTO
import com.packtpub.ProjectService
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono
import javax.validation.Validator


class ApiRoutes(private val projectService: ProjectService,
                private val validator: Validator) {

    @Bean
    fun apiRouter() =
        router {
            (accept(MediaType.APPLICATION_JSON_UTF8) and "/api").nest {
                "/project".nest {
                    POST("/save") { req ->
                        req.bodyToMono<ProjectDTO>()
                            .map { project ->
                                val violations = validator.validate(project)
                                if (violations.isNotEmpty()) {
                                    project.fieldErrors = violations.map {
                                        FieldErrorDTO(it.propertyPath.toString(), it.message)
                                    }
                                }
                                project
                            }
                            .flatMap {
                                when (it.fieldErrors) {
                                    null -> ServerResponse.ok().body(Mono.just(it))
                                    else -> ServerResponse.unprocessableEntity().body(Mono.just(it))
                                }
                            }
                    }
                }
            }
        }
}