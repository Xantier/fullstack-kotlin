package com.packtpub

interface ProjectService {
    fun saveProject(project: Project): String
    fun fetchProjects(): List<Project>
}

internal class ProjectServiceImpl : ProjectService {
    private var projects: List<Project> = listOf()

    override fun fetchProjects(): List<Project> = projects
    override fun saveProject(project: Project): String {
        println(project)
        projects += project
        return "'Saved' successfully"
    }
}