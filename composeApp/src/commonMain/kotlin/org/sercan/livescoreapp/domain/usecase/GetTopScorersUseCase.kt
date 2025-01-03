package org.sercan.livescoreapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.sercan.livescoreapp.domain.model.TopScorer
import org.sercan.livescoreapp.domain.repository.StandingsRepository

class GetTopScorersUseCase(
    private val repository: StandingsRepository
) {
    operator fun invoke(): Flow<List<TopScorer>> = repository.getTopScorers()
} 