package com.packtpub

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono


const val mercy: String = "application/vnd.github.mercy-preview+json"
const val drax: String = "application/vnd.github.drax-preview+json"

interface ProjectService {
    fun saveProject(project: Project): Mono<Project>
    fun fetchProjects(): List<Project>
    fun fetchProject(id: Long): Project?
    fun findByOwner(owner: String): List<Project>
    fun fetchAllOwners(): List<String>
    fun fetchProjectsForView(): List<ProjectView>
}

internal class ProjectServiceImpl
(private val projectRepository: ProjectRepository) : ProjectService {

    @Value("\${api.endpoint.url}")
    lateinit var endpoint: String

    override fun fetchProjects(): List<Project> =
        projectRepository.findAll().toList()

    override fun saveProject(project: Project): Mono<Project> {
        return project.toMono()
            .zipWith(fetchProjects(project), { it, githubApiDto ->
                it.copy(description = githubApiDto.description,
                    tags = githubApiDto.tags,
                    license = githubApiDto.license)
            }).map { fullproject ->
            projectRepository.save(fullproject)
        }
    }

    override fun fetchProject(id: Long): Project? =
        projectRepository.findById(id).orElse(null)

    override fun findByOwner(owner: String): List<Project> =
        projectRepository.findByOwner(owner)

    override fun fetchAllOwners(): List<String> =
        projectRepository.retrieveAllOwners()

    override fun fetchProjectsForView(): List<ProjectView> =
        projectRepository.retrieveAllProjectsForView()

    private fun fetchProjects(project: Project): Mono<GithubApiDto> {
        val webclient = WebClient.create(endpoint)
        return webclient.get()
            .uri("/repos/${project.owner}/${project.name}")
            .accept(MediaType.parseMediaType(mercy),
                MediaType.parseMediaType(drax))
            .exchange()
            .flatMap { response ->
                response.bodyToMono<GithubApiDto>()
            }
    }
}