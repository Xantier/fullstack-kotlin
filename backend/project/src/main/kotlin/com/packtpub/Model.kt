package com.packtpub

import javax.persistence.*

@Entity
@Table(name = "project")
data class Project(
    val name: String,
    val url: String,
    val owner: String,
    val language: Language,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val description: String? = null,

    @ElementCollection(fetch = FetchType.EAGER)
    val tags: List<String> = listOf(),
    val license: String? = null
)

enum class Language {
    KOTLIN, JAVASCRIPT, JAVA
}

data class ProjectView(val name: String, val url: String, val owner: String)