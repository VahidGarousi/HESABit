package ir.vbile.app.hesabit.feature_authentication.presentation

sealed class AuthenticateMode {
    object SignUp: AuthenticateMode()
    object SignIn: AuthenticateMode()
}