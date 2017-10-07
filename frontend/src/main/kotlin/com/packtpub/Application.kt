package com.packtpub

import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.form
import kotlinx.html.label
import react.RProps
import react.RState
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent


class Application : ReactDOMComponent<Application.Props, Application.State>() {
    companion object : ReactComponentSpec<Application, Props, State>

    init {
        state = State()
    }

    override fun ReactDOMBuilder.render() {
        div {
            form {
                div(classes = "form-group") {
                    label {
                        attributes["htmlFor"] = "numberInput"
                    }
                    NumberInput {
                        change = {
                            changeNumberValue(it)
                        }
                        value = state.numberValue
                    }
                }
                Button {
                    change = {
                        val newValue = state.numberValue + 1
                        changeNumberValue(newValue)
                    }
                    text = "Increment"
                }
                Button {
                    change = {
                        val newValue = state.numberValue - 1
                        changeNumberValue(newValue)
                    }
                    text = "Decrement"
                }
                div {
                    +"Current Value: ${state.numberValue}"
                }
            }
            a(href = "#list") {
                +"Go to list view"
            }
        }
    }

    private fun changeNumberValue(newValue: Int) {
        setState {
            numberValue = newValue
        }
    }

    class Props : RProps()
    class State(var numberValue: Int = 0) : RState
}