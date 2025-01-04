package org.sercan.livescoreapp.domain.repository

interface FootballRepository {
    suspend fun refreshData()
} 