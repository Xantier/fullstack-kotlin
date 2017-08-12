package com.packtpub

interface HelloSayer {
    fun sayHello()
}

internal class HelloWorld : HelloSayer {
    override fun sayHello(){
        println("Hello World")
    }
}