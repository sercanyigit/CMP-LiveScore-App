package org.sercan.livescoreapp

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sercan.livescoreapp.navigation.BottomNavItem
import org.sercan.livescoreapp.presentation.home.HomeScreen
import org.sercan.livescoreapp.presentation.standings.StandingsScreen

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun App() {
    MaterialTheme {
        var selectedItem by remember { mutableStateOf(0) }
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.Standings,
            BottomNavItem.Saved,
            BottomNavItem.Profile
        )

        Scaffold(
            bottomBar = {
                BottomNavigation(
                    backgroundColor = Color(0xFF1C1B1F),
                    contentColor = Color.White,
                    elevation = 0.dp,
                    modifier = Modifier.background(
                        brush = androidx.compose.ui.graphics.Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF1C1B1F).copy(alpha = 0f),
                                Color(0xFF1C1B1F)
                            )
                        )
                    )
                ) {
                    items.forEachIndexed { index, item ->
                        BottomNavigationItem(
                            icon = { 
                                Icon(
                                    item.icon,
                                    contentDescription = item.title,
                                    modifier = Modifier.size(24.dp)
                                ) 
                            },
                            label = { Text(item.title, fontSize = 12.sp) },
                            selected = selectedItem == index,
                            onClick = { selectedItem = index },
                            selectedContentColor = Color.White,
                            unselectedContentColor = Color.White.copy(alpha = 0.5f),
                            alwaysShowLabel = true
                        )
                    }
                }
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                AnimatedContent(
                    targetState = selectedItem,
                    transitionSpec = {
                        if (targetState > initialState) {
                            // Sağa doğru geçiş
                            (slideInHorizontally(
                                animationSpec = tween(300),
                                initialOffsetX = { it }
                            ) + fadeIn(animationSpec = tween(300)))
                                .togetherWith(
                                    slideOutHorizontally(
                                        animationSpec = tween(300),
                                        targetOffsetX = { -it }
                                    ) + fadeOut(animationSpec = tween(300))
                                )
                        } else {
                            // Sola doğru geçiş
                            (slideInHorizontally(
                                animationSpec = tween(300),
                                initialOffsetX = { -it }
                            ) + fadeIn(animationSpec = tween(300)))
                                .togetherWith(
                                    slideOutHorizontally(
                                        animationSpec = tween(300),
                                        targetOffsetX = { it }
                                    ) + fadeOut(animationSpec = tween(300))
                                )
                        }
                    }
                ) { index ->
                    when (index) {
                        0 -> HomeScreen()
                        1 -> StandingsScreen()
                        else -> HomeScreen()
                    }
                }
            }
        }
    }
}