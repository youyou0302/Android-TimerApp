package kr.co.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kr.co.presentation.theme.TimerAppTheme

@Composable
fun MainBottomBar(
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute: MainRoute = navBackStackEntry?.destination?.route?.let { currentRoute ->
        MainRoute.entries.find { route -> route.route == currentRoute }
    } ?: MainRoute.TIMER

    MainBottomBar(
        currentRoute = currentRoute,
        onItemClick = { newRoute ->
            navController.navigate(route = newRoute.route) {
                navController.graph.startDestinationRoute?.let {
                    popUpTo(it) {
                        saveState = true
                    }
                }
                this.launchSingleTop = true
                this.restoreState = true
            }
        }
    )
}

@Composable
private fun MainBottomBar(
    currentRoute: MainRoute,
    onItemClick: (MainRoute) -> Unit
) {
    Column(
        modifier = Modifier.background(Color.Black)
    ) {
        Divider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 70.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            MainRoute.entries.forEach { route ->
                IconButton(onClick = { onItemClick(route) }) {
                    Icon(
                        imageVector = route.icon,
                        contentDescription = route.contentDescription,
                        tint = if (currentRoute == route) {
                            Color.Red
                        } else {
                            Color.White
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MainBottomBarPreview() {
    TimerAppTheme {
        Surface {
            var currentRoute by remember { mutableStateOf(MainRoute.TIMER) }
            MainBottomBar(
                currentRoute = currentRoute,
                onItemClick = { newRoute -> currentRoute = newRoute }
            )
        }
    }
}