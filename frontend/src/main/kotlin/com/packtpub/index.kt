package com.packtpub

import com.packtpub.form.Form
import org.w3c.dom.events.EventListener
import react.RProps
import react.RState
import react.ReactComponentSpec
import react.dom.ReactDOM
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {
    val container = document.getElementById("container")
    fun render(currentHash: String = "") {
        ReactDOM.render(container) {
            StateComponent {
                hash = currentHash
            }
        }
    }

    window.addEventListener("hashchange", EventListener {
        val hash = window.location.hash.substring(1)
        render(hash)
    })
    render(window.location.hash.substring(1))
}


class StateComponent : ReactDOMComponent<StateComponent.Props, StateComponent.State>() {
    companion object : ReactComponentSpec<StateComponent, Props, State>

    init {
        state = State()
    }

    override fun ReactDOMBuilder.render() {
        when (props.hash) {
            "form" ->
                Form {
                    update = { it: Project ->
                        setState {
                            currentProject = it
                        }
                    }
                    submit = {
                        setState {
                            state.projectList += state.currentProject
                            state.currentProject = Project.identity()
                        }
                    }
                    project = state.currentProject
                }
            else   ->
                ProjectList {
                    items = state.projectList.asList()
                    action = {
                        println("Do nothing")
                    }
                }
        }
    }

    class Props(var hash: String) : RProps()
    class State(
        var projectList: Array<Project> = arrayOf(),
        var currentProject: Project = Project.identity()
    ) : RState
}
