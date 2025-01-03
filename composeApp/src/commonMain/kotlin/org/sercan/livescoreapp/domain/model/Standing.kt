package org.sercan.livescoreapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Standing(
    val position: Int,
    val club: Club,
    val played: Int,
    val won: Int,
    val drawn: Int,
    val lost: Int,
    val points: Int
)

@Serializable
data class Club(
    val name: String,
    val logo: String,
    val leagueType: LeagueType
)

@Serializable
enum class LeagueType {
    UEFA_CHAMPIONS_LEAGUE,
    EUROPA_LEAGUE
} 