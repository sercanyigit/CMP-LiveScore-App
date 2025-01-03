package org.sercan.livescoreapp.data.remote

import kotlinx.serialization.json.Json
import org.sercan.livescoreapp.data.model.FootballData
import org.sercan.livescoreapp.domain.model.*

class FootballApiImpl : FootballApi {
    private val json = Json { 
        ignoreUnknownKeys = true 
        isLenient = true
        prettyPrint = true
    }
    
    override suspend fun getLiveMatches(): List<Match> {
        return try {
            val jsonString = getJsonFromResource()
            val data = json.decodeFromString<FootballData>(jsonString)
            data.liveMatches
        } catch (e: Exception) {
            println("Error parsing live matches: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getUpcomingMatches(): List<Match> {
        return try {
            val jsonString = getJsonFromResource()
            val data = json.decodeFromString<FootballData>(jsonString)
            data.upcomingMatches
        } catch (e: Exception) {
            println("Error parsing upcoming matches: ${e.message}")
            emptyList()
        }
    }

    override suspend fun getFootballNews(): List<News> {
        return try {
            val jsonString = getJsonFromResource()
            val data = json.decodeFromString<FootballData>(jsonString)
            data.footballNews
        } catch (e: Exception) {
            println("Error parsing news: ${e.message}")
            emptyList()
        }
    }

    override suspend fun refreshData() {
        // Gerçek bir API'da burada refresh işlemi yapılır
    }

    private fun getJsonFromResource(): String {
        return try {
            val classLoader = this::class.java.classLoader
            val resource = classLoader.getResource("football_data.json")
            resource?.readText() ?: throw IllegalStateException("Resource not found")
        } catch (e: Exception) {
            throw IllegalStateException("Error reading JSON file: ${e.message}")
        }
    }
} 