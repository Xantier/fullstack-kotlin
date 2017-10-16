@file:Suppress("ArrayInDataClass")

package com.packtpub.store

import com.packtpub.model.Project
import redux.ActionPayload

enum class ActionType {
    HASH_CHANGE,
    FORM_SUBMIT,
    FORM_INPUT,
    FORM_CLEAR,
    SPINNING,
    POPULATE_PROJECTS
}

data class HashChange(val newHash: String) : ActionPayload
data class FormInput(val value: Any, val target: Any) : ActionPayload
data class FormSubmit(val project: Project) : ActionPayload
data class BooleanAction(val flag: Boolean) : ActionPayload
data class PopulateProject(val projects: Array<Project>) : ActionPayload
