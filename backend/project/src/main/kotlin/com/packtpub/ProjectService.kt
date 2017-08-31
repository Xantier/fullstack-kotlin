package com.packtpub

interface ProjectService {
    fun saveProject(project: Project): Project
    fun fetchProjects(): List<Project>
}

internal class ProjectServiceImpl
(private val projectRepository: ProjectRepository) : ProjectService {
    override fun fetchProjects(): List<Project> =
        projectRepository.findAll().toList()

    override fun saveProject(project: Project) =
        projectRepository.save(project)

}