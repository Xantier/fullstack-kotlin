package com.packtpub.form

import com.packtpub.Language
import kotlinx.html.*
import react.RProps
import react.RState
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent


class Form : ReactDOMComponent<Form.Props, Form.State>() {
    companion object : ReactComponentSpec<Form, Props, State>

    init {
        state = State()
    }

    override fun ReactDOMBuilder.render() {
        div(classes = "container"){
            form {
                div(classes = "form-group") {
                    label {
                        attributes["htmlFor"] = "name"
                        +"Name"
                    }
                    TextInput {
                        id = "name"
                        change = {
                            setState {
                                name = it
                            }
                        }
                        value = state.name
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
                            setState {
                                url = it
                            }
                        }
                        value = state.url
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
                            setState {
                                owner = it
                            }
                        }
                        value = state.owner
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
                            setState {
                                language = Language.valueOf(it)
                            }
                        }
                        options = Language.values().map { it.name }
                        value = state.language?.name
                    }
                }
            }
            Button {
                action = {
                    println(state)
                }
                text = "Submit"
            }
            Button {
                action = {
                    clearState()
                }
                text = "Clear Form"
            }
            a(href = "#list") {
                +"Go to list view"
            }
        }
    }

    private fun clearState() {
        setState {
            name = ""
            url = ""
            owner = ""
            language = null
        }
    }

    class Props : RProps()
    class State(var name: String = "",
                var url: String = "",
                var owner: String = "",
                var language: Language? = null) : RState
}