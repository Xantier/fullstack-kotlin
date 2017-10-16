package com.packtpub.store

import com.packtpub.Project
import redux.ReduxAction
import kotlin.browser.window


fun submitForm(proj: Project): ((dynamic) -> Unit, () -> ReduxStore) -> Unit {
    return { dispatch: (dynamic) -> Unit, getState: () -> ReduxStore ->
        dispatch(ReduxAction(ActionType.FORM_SUBMIT, FormSubmit(proj))())
        println("Synchronous Action")
        val (_, _, currentProject) = getState()
        window.setTimeout({
            dispatch(
                ReduxAction(ActionType.TEST_ASYNC, AsyncAction(currentProject))()
            )
        }, 3000)
    }
}