@file:Suppress("ArrayInDataClass")

package com.packtpub.store

import com.packtpub.Project
import redux.ReduxState



data class ReduxStore(
    val hash: String = "",
    val projectList: Array<Project> = arrayOf(),
    val currentProject: Project = Project.identity()
) : ReduxState
