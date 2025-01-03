package org.sercan.livescoreapp.data.model

import kotlinx.serialization.Serializable
import org.sercan.livescoreapp.domain.model.Match
import org.sercan.livescoreapp.domain.model.News
import org.sercan.livescoreapp.domain.model.Standing
import org.sercan.livescoreapp.domain.model.TopScorer

@Serializable
data class FootballData(
    val liveMatches: List<Match>,
    val upcomingMatches: List<Match>,
    val footballNews: List<News>,
    val standings: StandingsData
)

@Serializable
data class StandingsData(
    val tableStandings: List<Standing>,
    val topScorers: List<TopScorer>
) 