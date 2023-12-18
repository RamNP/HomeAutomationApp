package com.ram.homeautomation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ram.homeautomation.Screen.FirstFloorViewScreen
import com.ram.homeautomation.Screen.GroundFloorViewScreen
import com.ram.homeautomation.Screen.MainScreen


@Composable
fun NavigationScreenView() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = FloorList.MainScreen.route) {
        composable(FloorList.MainScreen.route) { }
        composable(FloorList.GroundFloorScreen.route) { GroundFloorViewScreen(navController) }
        composable(FloorList.FirstFloorScreen.route) { FirstFloorViewScreen(navController) }
    }
}
sealed class FloorList(val route: String) {
    object MainScreen : FloorList("MainScreen")
    object GroundFloorScreen : FloorList("GroundFloorScreen")
    object FirstFloorScreen : FloorList("FirstFloorScreen")

}

