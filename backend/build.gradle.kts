import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.compile
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

dependencies {
    compile(kotlin("stdlib"))
}

val project = mapOf(
    name to "backend"
)

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

val fatJar = task("fatJar", type = Jar::class) {
    baseName = "${project.name}-fat"
    manifest {
        attributes["Main-Class"] = "com.packtpub.HelloWorldKt"
    }
    from(
        configurations.runtime.map {
            if (it.isDirectory) it else zipTree(it)
        }
    )
    with(tasks["jar"] as CopySpec)
}

tasks {
    "build" {
        dependsOn(fatJar)
    }
}