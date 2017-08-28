package com.packtpub

interface ProjectService {
    fun saveProject(project: Project): Project
    fun fetchProjects(): List<Project>
}

internal class ProjectServiceImpl : ProjectService {
    private var projects: List<Project> = listOf()

    override fun fetchProjects(): List<Project> = projects
    override fun saveProject(project: Project): Project {
        println(project)
        projects += project
        return project
    }
}