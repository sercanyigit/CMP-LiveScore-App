package org.sercan.livescoreapp.data.remote

import JsonReader
import kotlinx.serialization.json.Json
import org.sercan.livescoreapp.data.model.FootballData
import org.sercan.livescoreapp.domain.model.*

class FootballApiImpl(
    private val jsonReader: JsonReader
) : FootballApi {
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

    override suspend fun getTableStandings(): List<Standing> {
        return try {
            val jsonString = getJsonFromResource()
            println("JSON String: $jsonString") // Debug log
            val data = json.decodeFromString<FootballData>(jsonString)
            println("Decoded data: $data") // Debug log
            data.standings.tableStandings
        } catch (e: Exception) {
            println("Error parsing standings: ${e.message}")
            e.printStackTrace() // Stack trace
            emptyList()
        }
    }

    override suspend fun getTopScorers(): List<TopScorer> {
        return try {
            val jsonString = getJsonFromResource()
            println("JSON String: $jsonString") // Debug log
            val data = json.decodeFromString<FootballData>(jsonString)
            println("Decoded data: $data") // Debug log
            data.standings.topScorers
        } catch (e: Exception) {
            println("Error parsing top scorers: ${e.message}")
            e.printStackTrace() // Stack trace
            emptyList()
        }
    }

    private fun getJsonFromResource(): String {
        return try {
            jsonReader.readJsonFromResources("football_data.json")
        } catch (e: Exception) {
            throw IllegalStateException("Error reading JSON file: ${e.message}")
        }
    }
} 