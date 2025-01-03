package org.sercan.livescoreapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.sercan.livescoreapp.domain.model.News
import org.sercan.livescoreapp.domain.repository.FootballRepository

class GetFootballNewsUseCase(
    private val repository: FootballRepository
) {
    operator fun invoke(): Flow<List<News>> = repository.getFootballNews()
} 