package com.packtpub.views

import com.packtpub.ProjectView
import kotlinx.html.*
import kotlinx.html.stream.createHTML


fun index(header: String, projects: List<ProjectView>): String {
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
            script(src = "/frontend/frontend.bundle.js")
        }

    }
}


