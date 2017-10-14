package redux

external interface ReduxState
external class Store {
    @JsName("getState")
    fun getState(): ReduxState

    @JsName("dispatch")
    fun doDispatch(action: dynamic)
}

@JsModule("redux")
@JsNonModule
external object Redux {
    @JsName("createStore")
    fun <ST : ReduxState> createStore(reducer: (ST, dynamic) -> ReduxState,
                                      initialState: ST,
                                      enhancer: (ST) -> ST = definedExternally)
        : Store
}


fun Store.dispatch(action: ReduxAction) {
    this.doDispatch(action())
}
