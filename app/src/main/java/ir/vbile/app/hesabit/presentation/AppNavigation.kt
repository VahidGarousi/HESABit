package ir.vbile.app.hesabit.presentation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import ir.vbile.app.hesabit.feature_authentication.presentation.Authentication
import ir.vbile.app.hesabit.feature_authentication.presentation.reset_password.ResetPasswordUI
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
                route = AuthenticationDirections.splash.destination,
                arguments = AuthenticationDirections.splash.arguments
            ) {
                Authentication()
            }
            composable(
                route = AuthenticationDirections.forgotPassword.destination,
                arguments = AuthenticationDirections.forgotPassword.arguments
            ) {
                ResetPasswordUI()
            }
        }
    }
}