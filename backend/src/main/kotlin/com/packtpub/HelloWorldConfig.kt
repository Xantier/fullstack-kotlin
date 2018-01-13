package com.packtpub

import org.springframework.context.annotation.Configuration
import org.springframework.context.support.GenericApplicationContext
import org.springframework.context.support.beans


@Configuration
open class HelloWorldConfig(ctx: GenericApplicationContext){
    init {
        beans {
            bean<HelloWorld>()
        }.initialize(ctx)
    }
}