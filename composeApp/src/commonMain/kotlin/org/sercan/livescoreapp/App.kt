package org.sercan.livescoreapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sercan.livescoreapp.presentation.navigation.BottomNavItem
import org.sercan.livescoreapp.presentation.home.HomeScreen

@Composable
fun App() {
    var selectedItem by remember { mutableStateOf(0) }
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Matches,
        BottomNavItem.Saved,
        BottomNavItem.Profile
    )

    MaterialTheme {
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
            Box(modifier = Modifier.padding(paddingValues)) {
                when (selectedItem) {
                    0 -> HomeScreen()
                    else -> HomeScreen()
                }
            }
        }
    }
}