package com.packtpub

import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.h1
import kotlinx.html.label
import react.RProps
import react.RState
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent
import kotlin.browser.window


class Application : ReactDOMComponent<Application.Props, Application.State>() {
    companion object : ReactComponentSpec<Application, Props, State>

    init {
        state = State()
    }

    override fun componentDidMount() {
        window.setTimeout({
            setState {
                showWarning = true
            }
        }, 5000)
    }

    override fun ReactDOMBuilder.render() {
        div {
            when (state.showWarning) {
                true -> h1 {
                    +"""YOU HAVE BEEN LOOKING AT THIS
                        APPLICATION FOR TOO LONG!"""
                }
                else -> div {
                    form {
                        div(classes = "form-group") {
                            label {
                                attributes["for"] = "numberInput"
                            }
                            NumberInput {
                                value = "4"
                            }
                        }
                    }
                    HelloComponent {
                        hello = "Hi"
                    }
                    WorldComponent {
                        world = "World"
                    }
                }
            }
        }
    }

    class Props : RProps()
    class State(var showWarning: Boolean = false) : RState
}