package org.sercan.livescoreapp.presentation.standings

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.sercan.livescoreapp.domain.model.Standing
import org.sercan.livescoreapp.domain.model.TopScorer
import org.sercan.livescoreapp.domain.usecase.GetTableStandingsUseCase
import org.sercan.livescoreapp.domain.usecase.GetTopScorersUseCase

data class StandingsScreenState(
    val standings: List<Standing> = emptyList(),
    val topScorers: List<TopScorer> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class StandingsViewModel(
    private val getTableStandingsUseCase: GetTableStandingsUseCase,
    private val getTopScorersUseCase: GetTopScorersUseCase
) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(StandingsScreenState())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            
            try {
                getTableStandingsUseCase().collect { standings ->
                    _state.value = _state.value.copy(
                        standings = standings,
                        isLoading = false
                    )
                }
                
                getTopScorersUseCase().collect { scorers ->
                    _state.value = _state.value.copy(
                        topScorers = scorers
                    )
                }
            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    isLoading = false,
                    error = e.message
                )
            }
        }
    }

    fun refresh() {
        loadData()
    }
} 