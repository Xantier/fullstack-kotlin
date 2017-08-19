plugins {
    id("org.jetbrains.kotlin.plugin.spring") version "1.1.4"
}
apply {
    plugin("org.springframework.boot")
}
dependencies {
    val springBootVersion: String = parent.properties["springBootVersion"] as String
    val kotlinxHtmlVersion: String = properties["kotlinxHtmlVersion"] as String
    compile(project(":backend:component"))
    compile("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
    compile("org.springframework.boot:spring-boot-devtools:$springBootVersion")
    compile("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxHtmlVersion")
}