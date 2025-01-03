package org.sercan.livescoreapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class News(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val date: String
) 