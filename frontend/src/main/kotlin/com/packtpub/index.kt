package com.packtpub

import org.w3c.dom.events.EventListener
import react.dom.ReactDOM
import react.dom.render
import redux.*
import kotlin.browser.document
import kotlin.browser.window

data class HashChange(val newHash: String) : ReduxState

fun main(args: Array<String>) {
    val reduxStore = Redux.createStore({ reduxState, reduxAction ->
        if (reduxAction.type == "@@INIT") {
            reduxState
        } else {
            when (ActionType.valueOf(reduxAction.type)) {
                ActionType.TEST -> {
                    val hashChange = reduxAction.payload as HashChange
                    reduxState.copy(hash = hashChange.newHash)
                }
            }
        }
    }, ReduxStore(),
        js("window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()")
    )
    val container = document.getElementById("container")
    fun render() {
        ReactDOM.render(container) {
            ProviderComponent {
                store = reduxStore
                children = RouterComponent.asConnectedComponent(routerComponent)
            }

        }
    }

    window.addEventListener("hashchange", EventListener {
        val hash = window.location.hash.substring(1)
        reduxStore.dispatch(ReduxAction(ActionType.TEST, HashChange(hash)))
    })
    reduxStore.dispatch(ReduxAction(ActionType.TEST, HashChange(window.location.hash.substring(1))))
    render()
}
