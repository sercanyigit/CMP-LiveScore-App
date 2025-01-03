package org.sercan.livescoreapp.data.model

import kotlinx.serialization.Serializable
import org.sercan.livescoreapp.domain.model.Match
import org.sercan.livescoreapp.domain.model.News

@Serializable
data class FootballData(
    val liveMatches: List<Match>,
    val upcomingMatches: List<Match>,
    val footballNews: List<News>
) 