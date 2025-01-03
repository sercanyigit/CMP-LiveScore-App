package org.sercan.livescoreapp.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sercan.livescoreapp.domain.model.Standing
import org.sercan.livescoreapp.domain.model.TopScorer

interface StandingsRepository {
    fun getTableStandings(): Flow<List<Standing>>
    fun getTopScorers(): Flow<List<TopScorer>>
    suspend fun refreshStandings()
} 