plugins {
    id("org.jetbrains.kotlin.plugin.spring") version "1.1.4-2"
}

dependencies {
    val springBootVersion: String = parent.properties["springBootVersion"] as String
    compile("org.springframework.boot:spring-boot-starter:$springBootVersion")
}