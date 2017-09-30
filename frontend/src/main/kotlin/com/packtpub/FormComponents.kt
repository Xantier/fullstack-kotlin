package com.packtpub

import kotlinx.html.InputType
import kotlinx.html.button
import kotlinx.html.id
import kotlinx.html.input
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.RProps
import react.ReactComponentStatelessSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMStatelessComponent


class NumberInput : ReactDOMStatelessComponent<NumberInput.Props>() {
    companion object : ReactComponentStatelessSpec<NumberInput, NumberInput.Props>

    override fun ReactDOMBuilder.render() {
        input(InputType.number, classes = "form-control") {
            onChangeFunction = { event: Event ->
                val value = event.currentTarget.asDynamic().value as String
                props.change(value.toInt())
            }
            id = props.id
            value = props.value.toString()
        }
    }

    class Props(var id: String = "numberInput",
                var value: Int,
                var change: (Int) -> Unit = {}) : RProps()
}


class Button : ReactDOMStatelessComponent<Button.Props>() {
    companion object : ReactComponentStatelessSpec<Button, Button.Props>

    override fun ReactDOMBuilder.render() {
        button(classes = "btn") {
            onClickFunction = { event: Event ->
                event.preventDefault()
                props.change()
            }
            +props.text
        }
    }

    class Props(var text: String,
                var change: () -> Unit = {}) : RProps()
}