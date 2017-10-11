package com.packtpub


data class Project(
    val name: String = "",
    val url: String = "",
    val owner: String = "",
    val language: Language?
) {
    companion object {
        fun identity() = Project(language = null)
    }
}

enum class Language { KOTLIN, JAVASCRIPT, JAVA }