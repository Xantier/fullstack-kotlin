package com.packtpub.views

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.packtpub.ProjectView
import kotlinx.html.*
import kotlinx.html.stream.createHTML


fun index(@Suppress("UNUSED_PARAMETER") header: String, @Suppress("UNUSED_PARAMETER") projects: List<ProjectView>): String {
    return createHTML(true).html {
        head {
            title = "Full Stack Kotlin"
            styleLink("https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css")
            styleLink("/static/css/spinner.css")
        }
        body {
            div {
                id = "container"
            }
            div {
                id = "contentHolder"
                script {
                    val writeValueAsString = jacksonObjectMapper().writeValueAsString(projects)
                    +"var __projects__ = '$writeValueAsString'"
                }
            }
            script(src = "/static/frontend.bundle.js")
        }
    }
}


