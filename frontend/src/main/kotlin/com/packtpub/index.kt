package com.packtpub

import org.w3c.dom.events.EventListener
import react.dom.ReactDOM
import react.dom.render
import redux.*
import kotlin.browser.document
import kotlin.browser.window

data class StateTest(val test: String) : ReduxState

fun main(args: Array<String>) {
    val reduxStore = Redux.createStore({ reduxState, reduxAction ->
        if (reduxAction.type == "@@INIT") {
            reduxState
        } else {
            when (ActionType.valueOf(reduxAction.type)) {
                ActionType.TEST -> {
                    val stateTest = reduxAction.payload as StateTest
                    reduxState.copy(test = stateTest.test)
                }
            }
        }
    }, StateTest("TEST STRING"),
        js("window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()")
    )
    val container = document.getElementById("container")
    fun render(currentHash: String = "") {
        ReactDOM.render(container) {
            RouterComponent {
                hash = currentHash
            }
        }
    }

    window.addEventListener("hashchange", EventListener {
        val hash = window.location.hash.substring(1)
        reduxStore.dispatch(ReduxAction(ActionType.TEST, StateTest(hash)))
    })
    render()
}
