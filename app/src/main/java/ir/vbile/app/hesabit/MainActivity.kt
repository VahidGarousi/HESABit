package ir.vbile.app.hesabit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ir.vbile.app.hesabit.navigation.NavigationManager
import ir.vbile.app.hesabit.presentation.AppNavigation
import ir.vbile.app.hesabit.core.di.presentation.ui.theme.HESABitTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationManager: NavigationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HESABitTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                navigationManager.commands.collectAsState().value.also { command ->
                    if (command.destination.isNotEmpty()) {
                        navController.navigate(command.destination)
                    }
                }
                AppNavigation(
                    navController,
                    scaffoldState
                )
            }
        }
    }
}