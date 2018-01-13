plugins {
    val kotlinVersion = "1.2.10"
    id("org.jetbrains.kotlin.plugin.spring") version kotlinVersion
}

dependencies {
    val springBootVersion: String = parent!!.properties["springBootVersion"] as String
    compile("org.springframework.boot:spring-boot-starter:$springBootVersion")
    compile("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    compile("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
}