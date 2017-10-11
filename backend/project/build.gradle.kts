plugins {
    val kotlinVersion = "1.1.51"
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
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