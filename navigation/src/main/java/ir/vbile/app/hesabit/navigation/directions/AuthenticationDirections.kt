package ir.vbile.app.hesabit.navigation.directions

import androidx.navigation.NamedNavArgument

object AuthenticationDirections {
    val root = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "auth"
    }

    val splash = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "splash"
    }

    val Default = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = ""
    }
    val dashboard = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "dashboard"
    }

    val signUp = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "sign_up"
    }

    val sign_in = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "sign_in"
    }

    val forgotPassword = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val destination = "forgot_password"
    }
}