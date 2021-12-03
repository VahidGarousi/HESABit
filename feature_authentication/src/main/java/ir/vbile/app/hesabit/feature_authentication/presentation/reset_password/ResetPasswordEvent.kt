package ir.vbile.app.hesabit.feature_authentication.presentation.reset_password

sealed class ResetPasswordEvent {
    data class EmailChanged(val email: String) : ResetPasswordEvent()
    object AuthenticateClicked : ResetPasswordEvent()
}
