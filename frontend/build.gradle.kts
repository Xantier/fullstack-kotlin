
import org.gradle.api.tasks.Copy
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.frontend.KotlinFrontendExtension
import org.jetbrains.kotlin.gradle.frontend.npm.NpmExtension
import org.jetbrains.kotlin.gradle.frontend.webpack.WebPackExtension
import org.jetbrains.kotlin.gradle.tasks.Kotlin2JsCompile

val prod: Boolean = (parent!!.properties["production"] as String ).toBoolean()

buildscript {
    val kotlinVersion: String = properties["kotlinVersion"] as String
    val frontendPluginVersion: String = properties["frontendPluginVersion"] as String
    repositories {
        jcenter()
        maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-frontend-plugin:$frontendPluginVersion")
    }
}

apply {
    plugin("kotlin2js")
    plugin("org.jetbrains.kotlin.frontend")
    if (prod) plugin("kotlin-dce-js")
}

repositories {
    jcenter()
    maven { setUrl("https://dl.bintray.com/kotlin/kotlin-eap") }
}
val kotlinVersion: String = properties["kotlinVersion"] as String
val kotlinxHtmlVersion: String = properties["kotlinxHtmlVersion"] as String
dependencies {
    "compile"(kotlin("stdlib-js", kotlinVersion))
    "compile"("org.jetbrains.kotlinx:kotlinx-html-js:$kotlinxHtmlVersion")
}

val compileKotlin2Js: Kotlin2JsCompile by tasks

compileKotlin2Js.kotlinOptions {
    sourceMap = true
    metaInfo = true
    freeCompilerArgs = listOf("-Xcoroutines=enable")
    outputFile = "${project.buildDir.path}/js/index.js"
    main = "call"
    moduleKind = "commonjs"
}

configure<KotlinFrontendExtension>{
    sourceMaps = true

    configure<NpmExtension> {
        replaceVersion("kotlinx-html-js", "0.6.4")
        replaceVersion("kotlinx-html-shared", "0.6.4")
        replaceVersion("kotlin-js-library", "1.1.51")

        dependency("react", "16.0.0")
        dependency("react-dom", "16.0.0")
        devDependency("source-map-loader")
    }

    bundle("webpack", delegateClosureOf<WebPackExtension> {
        publicPath = "/frontend/"
        port = 3000
        proxyUrl = "http://localhost:8080"
        stats = "verbose"
    })
}

if (prod) {
    val build by tasks
    val bundle by tasks
    build.dependsOn("runDceKotlinJs")
    bundle.dependsOn("runDceKotlinJs")
}
