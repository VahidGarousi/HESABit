package ir.vbile.app.hesabit.feature_authentication.presentation

import ir.vbile.app.hesabit.core.di.presentation.components.PasswordTextFieldState
import ir.vbile.app.hesabit.core.di.presentation.components.StandardTextFiledState
import ir.vbile.app.hesabit.core.di.util.UiText

class AuthenticationState(
    val emailAddress: StandardTextFiledState = StandardTextFiledState(),
    val password: PasswordTextFieldState = PasswordTextFieldState(),
    val authenticationMode: AuthenticateMode = AuthenticateMode.SignIn,
    val isLoading: Boolean = false,
    val success: Boolean? = null,
    val errorMessage: UiText? = null
) {

    companion object {
        fun initialise(): AuthenticationState = AuthenticationState()
    }

    fun build(block: Builder.() -> Unit) = Builder(this).apply(block).build()

    class Builder(state: AuthenticationState) {
        var userEmail = state.emailAddress
        var userPassword = state.password
        var mode: AuthenticateMode = state.authenticationMode
        var loading = state.isLoading
        var success = state.success
        var error = state.errorMessage

        fun build(): AuthenticationState {
            return AuthenticationState(
                userEmail,
                userPassword,
                mode,
                loading,
                success,
                error
            )
        }
    }
}