package org.schoolmanager.project.data.model

import kotlinx.serialization.Serializable


@Serializable
data class Contact(
    val id: String,
    val name: String,
    val surname: String,
    val messenger: String,
    val discord: String,
    val outlook: String,
    val dark_light: Boolean,
    val lang: String,
    val PP_URL: String
)
