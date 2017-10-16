package com.packtpub

import com.packtpub.form.Form
import com.packtpub.util.jsObject
import react.RProps
import react.RState
import react.ReactComponentSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMComponent
import redux.connect

val routerComponent =
    connect<RouterComponent.Props, ReduxStore>(
        { state: ReduxStore, props ->
            println(state.hash)
            jsObject<RouterComponent.Props> {
                hash = state.hash
            }
        }, null)

class RouterComponent : ReactDOMComponent<RouterComponent.Props, RouterComponent.State>() {
    companion object : ReactComponentSpec<RouterComponent, Props, State>

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

    class Props(var hash: String = "") : RProps()
    class State(
        var projectList: Array<Project> = arrayOf(),
        var currentProject: Project = Project.identity()
    ) : RState
}

