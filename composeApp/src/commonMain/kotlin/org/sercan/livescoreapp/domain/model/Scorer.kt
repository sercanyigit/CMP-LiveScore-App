package org.sercan.livescoreapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TopScorer(
    val position: Int,
    val player: String,
    val team: String,
    val goals: Int,
    val assists: Int
)

@Serializable
data class Player(
    val name: String,
    val club: String
) 