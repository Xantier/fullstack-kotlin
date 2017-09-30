package react.util

import org.w3c.dom.HTMLInputElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.events.Event
import kotlin.reflect.KClass

external fun require(module: String): dynamic

inline fun <T> jsObject(builder: T.() -> Unit): T {
    val obj: T = js("({})")
    return obj.apply {
        builder()
    }
}

inline fun js(builder: dynamic.() -> Unit): dynamic = jsObject(builder)

@Suppress("UnsafeCastFromDynamic")
fun Any.getOwnPropertyNames(): Array<String> {
    @Suppress("UNUSED_VARIABLE")
    val me = this
    return js("Object.getOwnPropertyNames(me)")
}

fun toPlainObjectStripNull(me: Any): dynamic {
    val obj = js("({})")
    for (p in me.getOwnPropertyNames().filterNot { it == "__proto__" || it == "constructor" }) {
        js("if (me[p] != null) { obj[p]=me[p] }")
    }
    return obj
}

fun jsstyle(builder: dynamic.() -> Unit): String = js(builder)

internal val Event.inputValue: String
    get() = (target as? HTMLInputElement)?.value ?: (target as? HTMLTextAreaElement)?.value ?: ""

fun <T : Any> KClass<T>.createInstance(): T {
    @Suppress("UNUSED_VARIABLE")
    val ctor = this.js

    return js("new ctor()")
}

