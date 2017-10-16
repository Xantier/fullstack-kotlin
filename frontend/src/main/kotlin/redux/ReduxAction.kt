package redux

import com.packtpub.store.ActionType
import com.packtpub.util.js

interface ActionPayload
class EmptyPayload: ActionPayload
class ReduxAction(private val type: ActionType, private val payload: ActionPayload = EmptyPayload()) {
    operator fun invoke(): dynamic {
        return js {
            this.type = type.name
            this.payload = payload
        }
    }
}