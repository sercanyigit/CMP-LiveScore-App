package org.sercan.livescoreapp.data.repository

import org.sercan.livescoreapp.data.remote.FootballApi
import org.sercan.livescoreapp.domain.repository.FootballRepository

class FootballRepositoryImpl(
    private val api: FootballApi
) : FootballRepository {

    override suspend fun refreshData() {
        api.refreshData()
    }
} 