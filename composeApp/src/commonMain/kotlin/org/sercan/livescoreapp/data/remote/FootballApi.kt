package org.sercan.livescoreapp.data.remote

import org.sercan.livescoreapp.domain.model.Match
import org.sercan.livescoreapp.domain.model.News

interface FootballApi {
    suspend fun getLiveMatches(): List<Match>
    suspend fun getUpcomingMatches(): List<Match>
    suspend fun getFootballNews(): List<News>
    suspend fun refreshData()
} 