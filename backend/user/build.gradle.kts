plugins {
    val kotlinVersion = "1.1.4-3"
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
}

dependencies {
    val springBootVersion: String = parent.properties["springBootVersion"] as String
    val springSecurityVersion: String = parent.properties["springSecurityVersion"] as String
    compile("org.springframework.boot:spring-boot-starter:$springBootVersion")
    compile("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    compile("org.springframework.security:spring-security-core:$springSecurityVersion")
    compile("org.springframework.security:spring-security-config:$springSecurityVersion")
    compile("org.springframework.security:spring-security-webflux:$springSecurityVersion")
}