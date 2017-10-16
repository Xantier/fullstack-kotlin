package com.packtpub

import com.packtpub.store.ActionType
import com.packtpub.store.HashChange
import com.packtpub.store.ReduxStore
import com.packtpub.store.mainReducer
import org.w3c.dom.events.EventListener
import react.dom.ReactDOM
import react.dom.render
import redux.*
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {
    val reduxStore = Redux.createStore(::mainReducer, ReduxStore(),
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
        reduxStore.dispatch(ReduxAction(ActionType.HASH_CHANGE, HashChange(hash)))
    })
    reduxStore.dispatch(ReduxAction(ActionType.HASH_CHANGE, HashChange(window.location.hash.substring(1))))
    render()
}
