package org.schoolmanager.project.data.model

import kotlinx.serialization.Serializable

@Serializable
//PARAMETERS OF DATA NEWS
data class NewsHomePage(
    val id: Int,
    val time: String,
    val title: String,
    val description: String)