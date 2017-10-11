package com.packtpub.form

import kotlinx.html.*
import kotlinx.html.js.onChangeFunction
import kotlinx.html.js.onClickFunction
import org.w3c.dom.events.Event
import react.RProps
import react.ReactComponentStatelessSpec
import react.dom.ReactDOMBuilder
import react.dom.ReactDOMStatelessComponent


class NumberInput : ReactDOMStatelessComponent<NumberInput.Props>() {
    companion object : ReactComponentStatelessSpec<NumberInput, Props>

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


class TextInput : ReactDOMStatelessComponent<TextInput.Props>() {
    companion object : ReactComponentStatelessSpec<TextInput, Props>

    override fun ReactDOMBuilder.render() {
        input(InputType.text, classes = "form-control") {
            onChangeFunction = { event: Event ->
                val value = event.currentTarget.asDynamic().value as String
                props.change(value)
            }
            id = props.id
            value = props.value
        }
    }

    class Props(var id: String = "numberInput",
                var value: String,
                var change: (String) -> Unit = {}) : RProps()
}


class Button : ReactDOMStatelessComponent<Button.Props>() {
    companion object : ReactComponentStatelessSpec<Button, Props>

    override fun ReactDOMBuilder.render() {
        button(classes = "btn btn-primary p-2 mr-2") {
            onClickFunction = { event: Event ->
                event.preventDefault()
                props.action()
            }
            +props.text
        }
    }

    class Props(var text: String,
                var action: () -> Unit = {}) : RProps()
}

class DropDown : ReactDOMStatelessComponent<DropDown.Props>() {
    companion object : ReactComponentStatelessSpec<DropDown, Props>

    override fun ReactDOMBuilder.render() {
        val defaultValue = "Select One"
        select(classes = "form-control") {
            option {
                +defaultValue
            }
            props.options.map {
                option {
                    +it
                }
            }
            onChangeFunction = { event: Event ->
                val value = event.currentTarget.asDynamic().value as String
                props.action(value)
            }
            attributes["value"] = props.value ?: defaultValue
        }
    }

    class Props(var options: List<String>,
                var value: String? = null,
                var action: (String) -> Unit = {}) : RProps()
}