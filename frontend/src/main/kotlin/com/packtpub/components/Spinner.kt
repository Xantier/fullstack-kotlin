package com.packtpub.components

import kotlinx.html.div
import kotlinx.html.id
import react.ReactComponentStaticSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMStaticComponent


class Spinner : ReactDOMStaticComponent() {
    companion object: ReactComponentStaticSpec<Spinner>

    override fun ReactDOMBuilder.render() {
        div {
            id = "loader"
            (0..7).map {
                div(classes = "dot")
            }
            div(classes = "loading")
        }
    }
}