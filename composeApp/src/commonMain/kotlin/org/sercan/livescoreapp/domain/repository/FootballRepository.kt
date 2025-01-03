package org.sercan.livescoreapp.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sercan.livescoreapp.domain.model.Match
import org.sercan.livescoreapp.domain.model.News

interface FootballRepository {
    fun getLiveMatches(): Flow<List<Match>>
    fun getUpcomingMatches(): Flow<List<Match>>
    fun getFootballNews(): Flow<List<News>>
    suspend fun refreshData()
} 