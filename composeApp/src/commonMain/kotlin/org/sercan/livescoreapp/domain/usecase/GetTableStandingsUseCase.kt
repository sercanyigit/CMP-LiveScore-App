package org.sercan.livescoreapp.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.sercan.livescoreapp.domain.model.Standing
import org.sercan.livescoreapp.domain.repository.StandingsRepository

class GetTableStandingsUseCase(
    private val repository: StandingsRepository
) {
    operator fun invoke(): Flow<List<Standing>> = repository.getTableStandings()
} 