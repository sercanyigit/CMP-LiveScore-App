package org.sercan.livescoreapp.presentation.home

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sercan.livescoreapp.domain.model.Match
import org.sercan.livescoreapp.domain.model.News

data class HomeScreenState(
    val liveMatches: List<Match> = emptyList(),
    val upcomingMatches: List<Match> = emptyList(),
    val news: List<News> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class HomeViewModel() : ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

        }
    }

    fun refresh() {
        loadData()
    }
} 