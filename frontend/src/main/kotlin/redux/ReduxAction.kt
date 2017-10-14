package redux

import com.packtpub.util.js


enum class ActionType { TEST }

class ReduxAction(private val type: ActionType, private val payload: Any) {
    operator fun invoke(): dynamic {
        return js {
            this.type = type.name
            this.payload = payload
        }
    }
}