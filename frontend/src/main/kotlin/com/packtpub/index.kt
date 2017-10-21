@file:Suppress("UnsafeCastFromDynamic")

package com.packtpub

import com.packtpub.components.RouterComponent
import com.packtpub.components.routerComponent
import com.packtpub.store.*
import org.w3c.dom.events.EventListener
import react.dom.ReactDOM
import react.dom.render
import redux.*
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {
    val reduxStore = Redux.createStore(::mainReducer, ReduxStore(),
        composeWithDevTools(Redux.applyMiddleware(ReduxThunk))
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
    reduxStore.dispatch(ReduxAction(ActionType.POPULATE_PROJECTS, PopulateProject(grabData())))
    reduxStore.doDispatch(fetchData())
    render()
}
