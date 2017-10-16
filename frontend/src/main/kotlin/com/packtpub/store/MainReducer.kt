package com.packtpub.store

import com.packtpub.Language
import com.packtpub.Project
import redux.ReduxState


fun mainReducer(reduxState: ReduxStore, reduxAction: dynamic): ReduxState =
    if (reduxAction.type == "@@INIT") {
        reduxState
    } else {
        when (ActionType.valueOf(reduxAction.type)) {
            ActionType.HASH_CHANGE -> {
                val hashChange = reduxAction.payload as HashChange
                reduxState.copy(hash = hashChange.newHash)
            }

            ActionType.FORM_SUBMIT -> {
                val newProject = reduxAction.payload as FormSubmit
                val newList = reduxState.projectList + newProject.project
                reduxState.copy(projectList = newList, currentProject = Project.identity())
            }
            ActionType.FORM_CLEAR  -> reduxState.copy(currentProject = Project.identity())
            ActionType.FORM_INPUT  -> {
                val formInput = reduxAction.payload as FormInput
                val currentProject = reduxState.currentProject
                val updatedProject = when (formInput.target) {
                    "name"     -> currentProject.copy(name = formInput.value as String)
                    "url"      -> currentProject.copy(url = formInput.value as String)
                    "owner"    -> currentProject.copy(owner = formInput.value as String)
                    "language" -> currentProject.copy(language = formInput.value as Language)
                    else       -> currentProject
                }
                reduxState.copy(currentProject = updatedProject)
            }
        }
    }