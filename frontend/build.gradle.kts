
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getValue
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.repositories
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

buildscript {
    val kotlinVersion: String = properties["kotlinVersion"] as String
    repositories {
        jcenter()
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

apply {
    plugin("kotlin2js")
}

repositories {
    jcenter()
}
val kotlinVersion: String = properties["kotlinVersion"] as String
dependencies {
    "compile"(kotlin("stdlib-js", kotlinVersion))
}

val compileKotlin2Js: Kotlin2JsCompile by tasks

compileKotlin2Js.kotlinOptions {
    sourceMap = true
    metaInfo = true
    freeCompilerArgs = listOf("-Xcoroutines=enable")
    outputFile = "${project.buildDir.path}/js/index.js"
    main = "call"
    //moduleKind = "commonjs"
}


val assembleWeb by tasks.creating(Copy::class) {
    dependsOn("classes")
    configurations["compile"].forEach { file ->
        from(zipTree(file.absolutePath)) {
            includeEmptyDirs = false
            include { fileTreeElement ->
                val path = fileTreeElement.path
                path.endsWith(".js") && path.startsWith("META-INF/resources/") || !path.startsWith("META_INF/")
            }
        }
    }
    from(compileKotlin2Js.destinationDir)
    into("${project.buildDir.path}/js")
}