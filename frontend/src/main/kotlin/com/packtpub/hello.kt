package com.packtpub

import org.w3c.xhr.XMLHttpRequest

fun main(args: Array<String>) {
    println("Hello Kotlin Developers")
    val xhr = XMLHttpRequest()
    xhr.open("GET", "http://localhost:8080/api/projects", true)
    xhr.onreadystatechange = {
        println(xhr.response)
    }
    xhr.send()
}