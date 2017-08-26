package com.packtpub


data class Project(
    val name: String,
    val url: String,
    val owner: String,
    val description: String?,
    val Language: Language,
    val tags: List<String>,
    val license: String?
)

enum class Language(val label: String) {
    KOTLIN("Kotlin"), JAVASCRIPT("JS"), JAVA("Java")
}