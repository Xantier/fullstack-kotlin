package com.packtpub.views

import com.packtpub.ProjectView
import kotlinx.html.*
import kotlinx.html.stream.createHTML


fun index(header: String, projects: List<ProjectView>): String {
    return createHTML(true).html {
        head {
            title = "Full Stack Kotlin"
            styleLink("/static/css/hello.css")
        }
        body {
            h4 { +header }
            p { +"Welcome to full stack Kotlin" }
            p {
                +"Our Resouces: "
                ul {
                    projects.map { (name, url, owner) ->
                        li {
                            a(url) {
                                target = ATarget.blank
                                +"$name by $owner"
                            }
                        }
                    }
                }
            }
            script(src = "/static/js/hello.js")
        }

    }
}

fun login(): String {
    return createHTML(true).html {
        head {
            title = "Full Stack Kotlin - Login"
            styleLink("/static/css/hello.css")
        }
        body {
            h4 { +"Please Long" }
            p {
                form(action = "login") {
                    input(type = InputType.text, name = "name")
                    input(type = InputType.password, name = "password")
                    submitInput(formMethod = InputFormMethod.post)
                }
            }
        }

    }
}



