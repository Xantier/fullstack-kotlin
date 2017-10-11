package com.packtpub

import com.packtpub.form.Form
import org.w3c.dom.events.EventListener
import react.dom.ReactDOM
import react.dom.render
import kotlin.browser.document
import kotlin.browser.window

fun main(args: Array<String>) {
    val container = document.getElementById("container")
    fun render(hash: String = "") {
        when (hash) {
            "form" -> ReactDOM.render(container) {
                Form {}
            }
            "list" -> ReactDOM.render(container) {
                ProjectList {
                    items = listOf()
                    action = {
                        println("Do nothing")
                    }
                }
            }
            else   -> ReactDOM.render(container) {
                Form {}
            }
        }

    }
    window.addEventListener("hashchange", EventListener {
        val hash = window.location.hash.substring(1)
        render(hash)
    })
    render(window.location.hash.substring(1))
}

