package com.packtpub.components.form

import com.packtpub.model.Language
import com.packtpub.model.Project
import kotlinx.html.*
import react.RProps
import react.ReactComponentStatelessSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMStatelessComponent


class Form : ReactDOMStatelessComponent<Form.Props>() {
    companion object : ReactComponentStatelessSpec<Form, Props>


    override fun ReactDOMBuilder.render() {
        div(classes = "container") {
            form {
                div(classes = "form-group") {
                    label {
                        attributes["htmlFor"] = "name"
                        +"Name"
                    }
                    TextInput {
                        id = "name"
                        change = {
                            props.update(it, "name")
                        }
                        value = props.project.name
                    }
                }
                div(classes = "form-group") {
                    label {
                        attributes["htmlFor"] = "url"
                        +"URL"
                    }
                    TextInput {
                        id = "url"
                        change = {
                            props.update(it, "url")
                        }
                        value = props.project.url
                    }
                }
                div(classes = "form-group") {
                    label {
                        attributes["htmlFor"] = "owner"
                        +"Owner"
                    }
                    TextInput {
                        id = "owner"
                        change = {
                            props.update(it, "owner")
                        }
                        value = props.project.owner
                    }
                }
                div(classes = "form-group") {
                    label {
                        attributes["htmlFor"] = "language"
                        +"Language"
                    }
                    DropDown {
                        id = "language"
                        action = {
                            props.update(Language.valueOf(it), "language")
                        }
                        options = Language.values().map { it.name }
                        value = props.project.language?.name
                    }
                }
            }
            div {
                Button {
                    action = props.submit
                    text = "Submit"
                }
                Button {
                    action = {
                        clearState()
                    }
                    text = "Clear Form"
                }
            }

            a(href = "#list") {
                +"Go to list view"
            }
        }
    }

    private fun clearState() {
        props.clear()
    }

    class Props(
        var project: Project,
        var update: (Any, String) -> Unit,
        var submit: () -> Unit,
        var clear: () -> Unit
    ) : RProps()
}