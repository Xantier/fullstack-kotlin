@file:Suppress("ArrayInDataClass")

package com.packtpub.store

import com.packtpub.model.Project
import redux.ReduxState



data class ReduxStore(
    val hash: String = "",
    val projectList: Array<Project> = arrayOf(),
    val currentProject: Project = Project.identity(),
    val isSpinning: Boolean = false
) : ReduxState
