@file:Suppress("UnsafeCastFromDynamic")

package com.packtpub.store

import com.packtpub.model.ExtraInfo
import com.packtpub.model.Language
import com.packtpub.model.Project
import com.packtpub.util.*
import redux.ReduxAction
import kotlin.browser.document


fun submitForm(proj: Project): ((dynamic) -> Unit, () -> ReduxStore) -> Unit {
    return { dispatch: (dynamic) -> Unit, _: () -> ReduxStore ->
        dispatch(ReduxAction(ActionType.SPINNING, BooleanAction(true))())
        dispatch(ReduxAction(ActionType.HASH_CHANGE, HashChange("list"))())
        async {
            val savedProject = postAndParseResult("api/projects/", jsObject {
                name = proj.name
                url = proj.url
                owner = proj.owner
                language = proj.language?.name ?: "KOTLIN"
            }, ::extractProject)
            dispatch(ReduxAction(ActionType.FORM_SUBMIT, FormSubmit(savedProject))())
            dispatch(ReduxAction(ActionType.SPINNING, BooleanAction(false))())
        }
    }
}

fun fetchData(): ((dynamic) -> Unit, () -> ReduxStore) -> Unit {
    return { dispatch: (dynamic) -> Unit, _: () -> ReduxStore ->
        dispatch(ReduxAction(ActionType.SPINNING, BooleanAction(true))())
        async {
            val projects = getAndParseResult("api/projects/") {
                (it as Array<dynamic>).map {
                    extractProject(it)
                }.toTypedArray()
            }
            dispatch(ReduxAction(ActionType.POPULATE_PROJECTS, PopulateProject(projects))())
            dispatch(ReduxAction(ActionType.SPINNING, BooleanAction(false))())
        }
    }
}

fun grabData(): Array<Project> {
    val unescape = require("lodash/unescape")
    val projectJson = js("__projects__")
    document.getElementById("contentHolder")?.remove()
    return JSON.parse<Array<dynamic>>(unescape(projectJson)).map {
        extractProject(it)
    }.toTypedArray()
}

private fun extractProject(it: dynamic): Project {
    val project = Project(
        it.name, it.url, it.owner, Language.valueOf(it.language), it.id
    )

    if (it.extrainfo != null) {
        val extraInfo = ExtraInfo(it.extraInfo.description, it.extraInfo.license,
            arrayOf(it.extraInfo.topics).asList())
        project.copy(extraInfo = extraInfo)
    }
    return project
}