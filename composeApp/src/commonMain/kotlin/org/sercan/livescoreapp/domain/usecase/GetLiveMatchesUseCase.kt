package org.sercan.livescoreapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.sercan.livescoreapp.domain.model.Match
import org.sercan.livescoreapp.domain.repository.FootballRepository

class GetLiveMatchesUseCase(
    private val repository: FootballRepository
) {
    operator fun invoke(): Flow<List<Match>> = repository.getLiveMatches()
} 