package org.sercan.livescoreapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.sercan.livescoreapp.data.remote.FootballApi
import org.sercan.livescoreapp.domain.model.Match
import org.sercan.livescoreapp.domain.model.News
import org.sercan.livescoreapp.domain.repository.FootballRepository

class FootballRepositoryImpl(
    private val api: FootballApi
) : FootballRepository {
    
    override fun getLiveMatches(): Flow<List<Match>> = flow {
        val matches = api.getLiveMatches()
        emit(matches)
    }

    override fun getUpcomingMatches(): Flow<List<Match>> = flow {
        val matches = api.getUpcomingMatches()
        emit(matches)
    }

    override fun getFootballNews(): Flow<List<News>> = flow {
        val news = api.getFootballNews()
        emit(news)
    }

    override suspend fun refreshData() {
        api.refreshData()
    }
} 