package org.sercan.livescoreapp.data.remote

import org.sercan.livescoreapp.domain.model.*

interface FootballApi {
    suspend fun getLiveMatches(): List<Match>
    suspend fun getUpcomingMatches(): List<Match>
    suspend fun getFootballNews(): List<News>
    suspend fun getTableStandings(): List<Standing>
    suspend fun getTopScorers(): List<TopScorer>
    suspend fun refreshData()
} 