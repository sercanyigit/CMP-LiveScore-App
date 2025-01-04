package org.sercan.livescoreapp.data.remote

import org.sercan.livescoreapp.domain.model.*

interface FootballApi {
    suspend fun getTableStandings(): List<Standing>
    suspend fun getTopScorers(): List<TopScorer>
    suspend fun refreshData()
} 