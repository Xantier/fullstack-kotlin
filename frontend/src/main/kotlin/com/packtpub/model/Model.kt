package com.packtpub.model

data class Project(
    val name: String = "",
    val url: String = "",
    val owner: String = "",
    val language: Language?,
    val id: Long? = null,
    val extraInfo: ExtraInfo? = ExtraInfo()
) {
    companion object {
        fun identity() = Project(language = null)
    }
}

data class ExtraInfo(
    val description: String? = "",
    val license: String? = null,
    val topics: List<String> = listOf()
)

enum class Language { KOTLIN, JAVASCRIPT, JAVA }

