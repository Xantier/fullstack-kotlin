package redux

import com.packtpub.util.require
import react.RProps
import react.ReactExternalComponentSpec


val Provider: dynamic = require("react-redux").Provider

class ReactProviderProps(var store: Any) : RProps()

object ProviderComponent : ReactExternalComponentSpec<ReactProviderProps>(Provider)
