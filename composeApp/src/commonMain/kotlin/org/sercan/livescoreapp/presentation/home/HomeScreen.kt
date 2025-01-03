package org.sercan.livescoreapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue

@Composable
fun HomeScreen() {
    val pagerState = rememberPagerState(pageCount = { 3 })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF1C1B1F))
    ) {
        TopAppBar(
            title = { Text("LiveScore", color = Color.White) },
            navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Menu, contentDescription = null, tint = Color.White)
                }
            },
            actions = {
                IconButton(onClick = {}) {
                    Icon(
                        Icons.Default.Notifications,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            backgroundColor = Color(0xFF1C1B1F),
            elevation = 0.dp
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            item {
                HorizontalPager(
                    state = pagerState,
                    contentPadding = PaddingValues(horizontal = 32.dp),
                    modifier = Modifier.fillMaxWidth()
                ) { page ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                            .graphicsLayer {
                                val pageOffset = (page - pagerState.currentPage).absoluteValue
                                
                                alpha = 1f - (pageOffset * 0.5f).coerceIn(0f, 1f)
                                scaleX = 1f - (pageOffset * 0.15f).coerceIn(0f, 1f)
                                scaleY = 1f - (pageOffset * 0.15f).coerceIn(0f, 1f)
                            },
                        shape = RoundedCornerShape(16.dp),
                        backgroundColor = Color(0xFF3D3A4F)
                    ) {
                        when (page) {
                            0 -> LiveMatchContent("Barcelona", "Man City", "2", "2")
                            1 -> LiveMatchContent("Arsenal", "Chelsea", "1", "0")
                            2 -> LiveMatchContent("Liverpool", "United", "3", "1")
                        }
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    MatchScheduleSection()
                }
            }

            item {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    FootballNewsSection()
                }
            }
        }
    }
}

@Composable
fun LiveMatchContent(team1: String, team2: String, score1: String, score2: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = androidx.compose.ui.graphics.Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF4B4BFF),
                        Color(0xFF9747FF)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "60:22",
                color = Color.White.copy(alpha = 0.7f),
                fontSize = 14.sp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Team 1 logo placeholder
                    Surface(
                        modifier = Modifier.size(40.dp),
                        shape = RoundedCornerShape(20.dp),
                        color = Color.White.copy(alpha = 0.1f)
                    ) {}
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(team1, color = Color.White)
                }
                
                Text(
                    "$score1 - $score2",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // Team 2 logo placeholder
                    Surface(
                        modifier = Modifier.size(40.dp),
                        shape = RoundedCornerShape(20.dp),
                        color = Color.White.copy(alpha = 0.1f)
                    ) {}
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(team2, color = Color.White)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("De Jong 66', Depay 79'", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
                Text("Alvarez 21', Palmer 70'", color = Color.White.copy(alpha = 0.7f), fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun MatchScheduleSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Match Schedule", color = Color.White, fontSize = 20.sp)
            TextButton(onClick = {}) {
                Text("See All", color = Color(0xFF4B4BFF))
            }
        }

        MatchScheduleItem("Chelsea", "Leicester", "27 Aug 2022", "01.40")
        Spacer(modifier = Modifier.height(8.dp))
        MatchScheduleItem("Brighton", "Leeds Unit", "27 Aug 2022", "00.10")
        Spacer(modifier = Modifier.height(8.dp))
        MatchScheduleItem("Man City", "Crystal Pa", "29 Aug 2022", "19.40")
    }
}

@Composable
fun MatchScheduleItem(team1: String, team2: String, date: String, time: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color(0xFF2D2D3A),
        shape = RoundedCornerShape(12.dp),
        elevation = 0.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Team 1 logo placeholder
                Surface(
                    modifier = Modifier.size(24.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White.copy(alpha = 0.1f)
                ) {}
                Spacer(modifier = Modifier.width(8.dp))
                Text(team1, color = Color.White)
            }
            
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(date, color = Color.Gray, fontSize = 12.sp)
                Text(time, color = Color.White, fontSize = 14.sp)
            }
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(team2, color = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                // Team 2 logo placeholder
                Surface(
                    modifier = Modifier.size(24.dp),
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White.copy(alpha = 0.1f)
                ) {}
            }
        }
    }
}

@Composable
fun FootballNewsSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Football News", color = Color.White, fontSize = 20.sp)
            TextButton(onClick = {}) {
                Text("See All", color = Color(0xFF4B4BFF))
            }
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = Color(0xFF2D2D3A),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "Champions League 2022-23 draw: group stage analysis and predictions",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
} 