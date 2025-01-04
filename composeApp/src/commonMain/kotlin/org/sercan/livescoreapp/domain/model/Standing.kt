package org.sercan.livescoreapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Standing(
    val position: Int,
    val team: String,
    val played: Int,
    val won: Int,
    val drawn: Int,
    val lost: Int,
    val goalsFor: Int,
    val goalsAgainst: Int,
    val points: Int
)

@Serializable
enum class LeagueType {
    UEFA_CHAMPIONS_LEAGUE,
    EUROPA_LEAGUE
} 