package org.sercan.livescoreapp.di

import JsonReader
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import org.sercan.livescoreapp.data.remote.FootballApi
import org.sercan.livescoreapp.data.remote.FootballApiImpl
import org.sercan.livescoreapp.data.repository.FootballRepositoryImpl
import org.sercan.livescoreapp.data.repository.StandingsRepositoryImpl
import org.sercan.livescoreapp.domain.repository.FootballRepository
import org.sercan.livescoreapp.domain.repository.StandingsRepository
import org.sercan.livescoreapp.domain.usecase.*
import org.sercan.livescoreapp.presentation.home.HomeViewModel
import org.sercan.livescoreapp.presentation.standings.StandingsViewModel

val appModule = module {
    // JSON Reader
    single { JsonReader() }
    
    // API
    single<FootballApi> { FootballApiImpl(get()) }

    // Repositories
    single<FootballRepository> { FootballRepositoryImpl(get()) }
    single<StandingsRepository> { StandingsRepositoryImpl(get()) }

    // Use Cases
    factoryOf(::GetLiveMatchesUseCase)
    factoryOf(::GetUpcomingMatchesUseCase)
    factoryOf(::GetFootballNewsUseCase)
    factoryOf(::GetTableStandingsUseCase)
    factoryOf(::GetTopScorersUseCase)

    // ViewModels
    factory { HomeViewModel(get(), get(), get()) }
    factory { StandingsViewModel(get(), get()) }
} 