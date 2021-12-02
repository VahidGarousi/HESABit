package ir.vbile.app.hesabit.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ir.vbile.app.hesabit.navigation.directions.AuthenticationDirections

@Composable
fun AppNavigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = AuthenticationDirections.root.destination
    ) {
        navigation(
            startDestination = AuthenticationDirections.splash.destination,
            route = AuthenticationDirections.root.destination
        ) {
            composable(
                route = AuthenticationDirections.root.destination,
                arguments = AuthenticationDirections.splash.arguments
            ) {

            }
        }
    }
}