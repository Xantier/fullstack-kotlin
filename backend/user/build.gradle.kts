plugins {
    val kotlinVersion = "1.1.51"
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
}

dependencies {
    val springBootVersion: String = parent!!.properties["springBootVersion"] as String
    val springSecurityVersion: String = parent!!.properties["springSecurityVersion"] as String
    val postgresVersion: String = parent!!.properties["postgresVersion"] as String
    compile("org.springframework.boot:spring-boot-starter:$springBootVersion")
    compile("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    compile("org.postgresql:postgresql:$postgresVersion")
    compile("org.springframework.security:spring-security-core:$springSecurityVersion")
    compile("org.springframework.security:spring-security-config:$springSecurityVersion")
    compile("org.springframework.security:spring-security-webflux:$springSecurityVersion")
}