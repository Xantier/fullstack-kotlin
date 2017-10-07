package com.packtpub

import kotlinx.html.a
import kotlinx.html.div
import org.w3c.dom.events.EventListener
import react.RProps
import react.RState
import react.ReactComponentSpec
import react.ReactComponentStatelessSpec
import react.dom.*
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {
    val container = document.getElementById("container")
    fun render(hash: String = "") {
        when (hash) {
            "form" -> ReactDOM.render(container) {
                Application {}
            }
            "list" -> ReactDOM.render(container) {
                HelloComponent {
                    hello = "Hello"
                }
            }
            else   -> ReactDOM.render(container) {
                Application {}
            }
        }

    }
    window.addEventListener("hashchange", EventListener {
        val hash = window.location.hash.substring(1)
        render(hash)
    })
    render()
}

class HelloComponent : ReactDOMComponent
<HelloComponent.Props, HelloComponent.State>() {
    companion object : ReactComponentSpec<HelloComponent, Props, State>

    init {
        state = State()
    }

    override fun ReactDOMBuilder.render() {
        div {
            div {
                +props.hello
            }
            div {
                a(href = "#form") {
                    +"Go to form view"
                }
            }
        }
    }

    class Props(var hello: String) : RProps()
    class State : RState
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