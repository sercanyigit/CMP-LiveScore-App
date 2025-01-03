package org.sercan.livescoreapp.presentation.home

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sercan.livescoreapp.domain.model.Match
import org.sercan.livescoreapp.domain.model.News
import org.sercan.livescoreapp.domain.usecase.GetFootballNewsUseCase
import org.sercan.livescoreapp.domain.usecase.GetLiveMatchesUseCase
import org.sercan.livescoreapp.domain.usecase.GetUpcomingMatchesUseCase

data class HomeScreenState(
    val liveMatches: List<Match> = emptyList(),
    val upcomingMatches: List<Match> = emptyList(),
    val news: List<News> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class HomeViewModel(
    private val getLiveMatchesUseCase: GetLiveMatchesUseCase,
    private val getUpcomingMatchesUseCase: GetUpcomingMatchesUseCase,
    private val getFootballNewsUseCase: GetFootballNewsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            
            try {
                getLiveMatchesUseCase().collect { matches ->
                    _state.value = _state.value.copy(
                        liveMatches = matches,
                        isLoading = false
                    )
                }
                
                getUpcomingMatchesUseCase().collect { matches ->
                    _state.value = _state.value.copy(
                        upcomingMatches = matches
                    )
                }
                
                getFootballNewsUseCase().collect { news ->
                    _state.value = _state.value.copy(
                        news = news
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