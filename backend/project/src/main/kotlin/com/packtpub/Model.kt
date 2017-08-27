package com.packtpub


data class Project(
    val name: String,
    val url: String,
    val owner: String,
    val description: String?,
    val language: Language,
    val tags: List<String>,
    val license: String?
)

enum class Language {
    KOTLIN, JAVASCRIPT, JAVA
}