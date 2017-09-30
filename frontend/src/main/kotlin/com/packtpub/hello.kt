package com.packtpub

import kotlinx.html.div
import react.RProps
import react.RState
import react.ReactComponentSpec
import react.ReactComponentStatelessSpec
import react.dom.*
import kotlin.browser.document

fun main(args: Array<String>) {
    ReactDOM.render(document.getElementById("container")) {
        div {
            HelloComponent {
                hello = "Hi"
            }
            WorldComponent {
                world = "World"
            }
        }
    }
}

class HelloComponent : ReactDOMComponent
<HelloComponent.Props, HelloComponent.State>() {
    companion object : ReactComponentSpec<HelloComponent, Props, State>

    init {
        state = State()
    }

    override fun ReactDOMBuilder.render() {
        div {
            +props.hello
        }
    }

    class Props(var hello: String) : RProps()
    class State(var showWarning: Boolean = false) : RState
}

class WorldComponent : ReactDOMStatelessComponent<WorldComponent.Props>() {
    companion object : ReactComponentStatelessSpec<WorldComponent, Props>

    override fun ReactDOMBuilder.render() {
        div {
            +props.world
        }
    }

    class Props(var world: String) : RProps()
}