package com.packtpub

import kotlinx.html.*
import kotlinx.html.js.onClickFunction
import react.RProps
import react.ReactComponentStatelessSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMStatelessComponent


class ProjectList : ReactDOMStatelessComponent<ProjectList.Props>() {
    companion object : ReactComponentStatelessSpec<ProjectList, Props>

    override fun ReactDOMBuilder.render() {
        div(classes = "container") {
            div(classes = "row") {
                div(classes = "col-12") {
                    h1 {
                        +"Projects"
                    }
                    a(href = "#form", classes = "float-right") {
                        +"Go to form view"
                    }
                }
            }

            table(classes = "table table-inverse table-hover") {
                thead {
                    tr {
                        th {
                            +"Name"
                        }
                        th {
                            +"Owner"
                        }
                        th {
                            +"Language"
                        }
                        th {
                            +"Url"
                        }
                    }
                }
                tbody {
                    props.items.map { project: Project ->
                        tr {

                            td {
                                +project.name
                            }
                            td {
                                +project.owner
                            }
                            td {
                                +(project.language?.name ?: "")
                            }
                            td {
                                a(project.url) {
                                    onClickFunction = {
                                        props.action(project)
                                    }
                                    +project.url
                                }
                            }


                        }
                    }
                }
            }
        }

    }

    class Props(var items: List<Project>,
                var action: (Project) -> Unit = {}) : RProps()
}
