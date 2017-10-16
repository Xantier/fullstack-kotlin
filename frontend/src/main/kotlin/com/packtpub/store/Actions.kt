package com.packtpub.store

import com.packtpub.Project
import redux.ActionPayload

enum class ActionType {
    HASH_CHANGE,
    FORM_SUBMIT,
    FORM_INPUT,
    FORM_CLEAR,
    TEST_ASYNC
}

data class HashChange(val newHash: String) : ActionPayload
data class FormInput(val value: Any, val target: Any) : ActionPayload
data class FormSubmit(val project: Project) : ActionPayload
data class AsyncAction(val project: Project) : ActionPayload
