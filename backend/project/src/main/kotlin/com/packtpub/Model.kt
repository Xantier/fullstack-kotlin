package com.packtpub


data class Project(
    val name: String,
    val url: String,
    val owner: String,
    val language: Language,
    val description: String? = null,
    val tags: List<String> = listOf(),
    val license: String? = null
)

enum class Language {
    KOTLIN, JAVASCRIPT, JAVA
}