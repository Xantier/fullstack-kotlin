package redux

import com.packtpub.util.createInstance
import react.*


inline fun <reified T : ReactComponent<P, S>, reified P : RProps, S : RState>
    ReactComponentSpec<T, P, S>.asConnectedElement(
    connectFunction: (Any) -> ReactElement, props: P = P::class.createInstance()): Any {
    val wrap = ReactComponent.wrap(T::class)
    return React.createElement(
        connectFunction(ReactBuilder.Node(wrap, props).type), null)
}


inline fun <reified T : ReactComponent<P, S>, reified P : RProps, S : RState>
    ReactComponentSpec<T, P, S>.asConnectedComponent(
    connectFunction: (Any) -> ReactElement, props: P = P::class.createInstance()): ReactExternalComponentSpec<P> {
    val wrap = ReactComponent.wrap(T::class)
    return ReactExternalComponentSpec(
        connectFunction(ReactBuilder.Node(wrap, props).type).asDynamic()
    )
}