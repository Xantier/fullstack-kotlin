package com.packtpub

interface HelloSayer {
    fun sayHello(name: String): String
}

internal class HelloWorld : HelloSayer {
    override fun sayHello(name: String) =
        "Hello $name"

}