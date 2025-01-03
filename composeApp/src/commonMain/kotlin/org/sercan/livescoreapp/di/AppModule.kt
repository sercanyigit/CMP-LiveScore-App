package org.sercan.livescoreapp.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import org.sercan.livescoreapp.data.remote.FootballApi
import org.sercan.livescoreapp.data.remote.FootballApiImpl
import org.sercan.livescoreapp.data.repository.FootballRepositoryImpl
import org.sercan.livescoreapp.domain.repository.FootballRepository
import org.sercan.livescoreapp.domain.usecase.GetFootballNewsUseCase
import org.sercan.livescoreapp.domain.usecase.GetLiveMatchesUseCase
import org.sercan.livescoreapp.domain.usecase.GetUpcomingMatchesUseCase
import org.sercan.livescoreapp.presentation.home.HomeViewModel

val appModule = module {
    // API
    single<FootballApi> { FootballApiImpl() }

    // Repository
    single<FootballRepository> { FootballRepositoryImpl(get()) }

    // Use Cases
    factoryOf(::GetLiveMatchesUseCase)
    factoryOf(::GetUpcomingMatchesUseCase)
    factoryOf(::GetFootballNewsUseCase)

    // ViewModels
    factoryOf(::HomeViewModel)
} 