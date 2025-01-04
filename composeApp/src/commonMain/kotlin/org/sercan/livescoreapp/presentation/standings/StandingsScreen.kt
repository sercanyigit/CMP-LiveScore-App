package org.sercan.livescoreapp.presentation.standings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.sercan.livescoreapp.domain.model.LeagueType
import org.sercan.livescoreapp.domain.model.Standing
import org.sercan.livescoreapp.domain.model.TopScorer
import org.sercan.livescoreapp.domain.usecase.GetTableStandingsUseCase
import org.sercan.livescoreapp.domain.usecase.GetTopScorersUseCase

private object ViewModelFactory : KoinComponent {
    fun createViewModel() = StandingsViewModel(
        get<GetTableStandingsUseCase>(),
        get<GetTopScorersUseCase>()
    )
}

@Composable
fun StandingsScreen() {
    val viewModel = getViewModel(
        key = "standings-screen",
        factory = viewModelFactory {
            ViewModelFactory.createViewModel()
        }
    )

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1B1F))
    ) {
        TopAppBar(
            title = { Text("Standings", color = Color.White) },
            backgroundColor = Color(0xFF1C1B1F),
            elevation = 0.dp
        )

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color.White)
                }
            }
            state.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.error ?: "Unknown error",
                        color = Color.Red
                    )
                }
            }
            else -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp)
                ) {
                    item {
                        TableStandingsSection(state.standings)
                    }
                    item {
                        GoalsScoredSection(state.topScorers)
                    }
                }
            }
        }
    }
}

@Composable
fun TableStandingsSection(standings: List<Standing>) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Table Standings", color = Color.White, fontSize = 20.sp)
            TextButton(onClick = {}) {
                Text("See All", color = Color(0xFF4B4BFF))
            }
        }

        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Club", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.weight(1f))
                Text("W", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.width(30.dp))
                Text("D", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.width(30.dp))
                Text("L", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.width(30.dp))
                Text("Poin", color = Color.Gray, fontSize = 14.sp, modifier = Modifier.width(40.dp))
            }

            standings.forEach { standing ->
                StandingItem(standing)
                if (standing != standings.last()) {
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color(0xFF4B4BFF), CircleShape)
                )
                Text("UEFA Champions league", color = Color.Gray, fontSize = 12.sp)
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(Color(0xFFFFA726), CircleShape)
                )
                Text("Europa League", color = Color.Gray, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun StandingItem(standing: Standing) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .background(
                        when (standing.position) {
                            in 1..4 -> Color(0xFF4B4BFF) // Champions League spots
                            5 -> Color(0xFFFFA726) // Europa League spot
                            else -> Color.Gray
                        },
                        CircleShape
                    )
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            
            Text(standing.team, color = Color.White)
        }

        Text(
            standing.won.toString(),
            color = Color.White,
            modifier = Modifier.width(30.dp),
            textAlign = TextAlign.Center
        )
        Text(
            standing.drawn.toString(),
            color = Color.White,
            modifier = Modifier.width(30.dp),
            textAlign = TextAlign.Center
        )
        Text(
            standing.lost.toString(),
            color = Color.White,
            modifier = Modifier.width(30.dp),
            textAlign = TextAlign.Center
        )
        Text(
            standing.points.toString(),
            color = Color.White,
            modifier = Modifier.width(40.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun GoalsScoredSection(scorers: List<TopScorer>) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Goals Scored", color = Color.White, fontSize = 20.sp)
            TextButton(onClick = {}) {
                Text("See All", color = Color(0xFF4B4BFF))
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            scorers.forEach { scorer ->
                ScorerItem(scorer)
            }
        }
    }
}

@Composable
fun ScorerItem(scorer: TopScorer) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(scorer.player, color = Color.White)
            Text(scorer.team, color = Color.Gray, fontSize = 12.sp)
        }

        Text(
            scorer.goals.toString(),
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
} 