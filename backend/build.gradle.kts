import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

buildscript {
    val springBootVersion: String = properties["springBootVersion"] as String
    repositories {
        maven { setUrl("https://repo.spring.io/milestone") }
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    }
}

subprojects {
    repositories {
        mavenCentral()
        maven { setUrl("https://repo.spring.io/milestone") }
        maven { setUrl("https://repo.spring.io/snapshot") }
    }

    plugins {
        kotlin("jvm")
    }

    apply {
        plugin("kotlin")
    }

    dependencies {
        compile(kotlin("stdlib-jre8"))
        compile(kotlin("reflect"))
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}