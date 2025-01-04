package org.sercan.livescoreapp.data.model

import kotlinx.serialization.Serializable
import org.sercan.livescoreapp.domain.model.Standing
import org.sercan.livescoreapp.domain.model.TopScorer

@Serializable
data class FootballData(
    val standings: List<Standing>,
    val topScorers: List<TopScorer>
)

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
data class TopScorer(
    val position: Int,
    val player: String,
    val team: String,
    val goals: Int,
    val assists: Int
) 