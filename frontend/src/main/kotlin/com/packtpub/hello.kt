package com.packtpub

import org.w3c.xhr.XMLHttpRequest

fun main(args: Array<String>) {
    println("Hello Webpack")
    val xhr = XMLHttpRequest()
    xhr.open("GET", "/api/projects", true)
    xhr.onreadystatechange = {
        println(xhr.response)
    }
    xhr.send()
}