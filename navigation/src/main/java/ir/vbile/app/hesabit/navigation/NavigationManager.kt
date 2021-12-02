package ir.vbile.app.hesabit.navigation

import ir.vbile.app.hesabit.navigation.directions.AuthenticationDirections.Default
import ir.vbile.app.hesabit.navigation.directions.NavigationCommand
import kotlinx.coroutines.flow.MutableStateFlow

class NavigationManager {
    var commands = MutableStateFlow(Default)
    fun navigate(
        directions: NavigationCommand
    ) {
        commands.value = directions
    }
}