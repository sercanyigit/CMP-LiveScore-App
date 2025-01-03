package org.sercan.livescoreapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TopScorer(
    val player: Player,
    val goals: Int
)

@Serializable
data class Player(
    val name: String,
    val photo: String,
    val club: String
) 