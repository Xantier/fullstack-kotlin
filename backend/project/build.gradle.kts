plugins {
    id("org.jetbrains.kotlin.plugin.spring") version "1.1.50"
    id("org.jetbrains.kotlin.plugin.jpa") version "1.1.50"
}

dependencies {
    val springBootVersion: String = parent!!.properties["springBootVersion"] as String
    val postgresVersion: String = parent!!.properties["postgresVersion"] as String
    val kotlinJacksonVersion: String = properties["kotlinJacksonVersion"] as String
    compile("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
    compile("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    compile("org.postgresql:postgresql:$postgresVersion")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:$kotlinJacksonVersion")
}