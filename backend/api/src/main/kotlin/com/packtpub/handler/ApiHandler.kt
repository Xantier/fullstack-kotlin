package com.packtpub.handler

import com.packtpub.FieldErrorDTO
import com.packtpub.ProjectDTO
import com.packtpub.ProjectService
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import javax.validation.Validator


class ApiHandler(private val validator: Validator,
                 private val projectService: ProjectService) {

    fun handle(req: ServerRequest) =
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