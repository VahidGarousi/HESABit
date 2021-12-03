package ir.vbile.app.hesabit.feature_authentication.presentation

sealed class AuthenticationEvent {
    object AuthenticationModeToggled : AuthenticationEvent()
    object DismissErrorDialog : AuthenticationEvent()
    object AuthenticateClicked : AuthenticationEvent()
    object ForgotPasswordClicked : AuthenticationEvent()
    object TogglePasswordVisibility : AuthenticationEvent()
    data class EmailChanged(val email: String) : AuthenticationEvent()
    data class PasswordChanged(val password: String) : AuthenticationEvent()
}