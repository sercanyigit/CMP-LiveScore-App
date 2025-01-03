package org.sercan.livescoreapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.sercan.livescoreapp.data.remote.FootballApi
import org.sercan.livescoreapp.domain.model.Standing
import org.sercan.livescoreapp.domain.model.TopScorer
import org.sercan.livescoreapp.domain.repository.StandingsRepository

class StandingsRepositoryImpl(
    private val api: FootballApi
) : StandingsRepository {
    override fun getTableStandings(): Flow<List<Standing>> = flow {
        try {
            val standings = api.getTableStandings()
            emit(standings)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    override fun getTopScorers(): Flow<List<TopScorer>> = flow {
        try {
            val scorers = api.getTopScorers()
            emit(scorers)
        } catch (e: Exception) {
            emit(emptyList())
        }
    }

    override suspend fun refreshStandings() {
        api.refreshData()
    }
} 