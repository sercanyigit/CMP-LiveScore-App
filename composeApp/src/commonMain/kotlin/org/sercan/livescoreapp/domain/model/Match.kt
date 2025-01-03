package org.sercan.livescoreapp.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val name: String,
    val logo: String,
    val scorers: List<Scorer> = emptyList(),
    val score: Int = 0
)

@Serializable
data class Scorer(
    val name: String,
    val minute: Int
)

@Serializable
data class Match(
    val id: String,
    val homeTeam: Team,
    val awayTeam: Team,
    val matchTime: String? = null,
    val date: String? = null,
    val time: String? = null,
    val status: MatchStatus = MatchStatus.UPCOMING
)

@Serializable
enum class MatchStatus {
    LIVE, UPCOMING, FINISHED
} 