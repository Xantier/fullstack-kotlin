package com.packtpub.handler

import com.packtpub.*
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.bodyToMono
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono
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
                    null -> ServerResponse.ok().body(
                        projectService.saveProject(it.toProject()).toDto().toMono()
                    )
                    else -> ServerResponse.unprocessableEntity().body(it.toMono())
                }
            }

    fun getProjects(req: ServerRequest) =
        ServerResponse.ok().body(
            projectService.fetchProjects().map { it.toDto() }.toMono()
        )

    fun getProject(req: ServerRequest): Mono<ServerResponse> {
        val id = req.pathVariable("id").toLong()
        val projectDTO: ProjectDTO? = projectService.fetchProject(id)?.toDto()
        return if (projectDTO != null) {
            ServerResponse.ok().body(projectDTO.toMono())
        } else {
            ServerResponse.notFound().build()
        }
    }

    fun getOwners(req: ServerRequest): Mono<ServerResponse> =
        ServerResponse.ok().body(projectService.fetchAllOwners().toMono())

    fun getByOwner(req: ServerRequest): Mono<ServerResponse> {
        val name = req.pathVariable("name")
        return ServerResponse.ok().body(projectService.findByOwner(name).map { it.toDto() }.toMono())
    }
}