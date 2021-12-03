package ir.vbile.app.hesabit.feature_authentication.presentation

interface AuthenticateView {
    fun authenticate()

    fun signUp()

    fun signIn()

    fun toggleAuthenticationMode()

    fun setEmailAddress(emailAddress: String)

    fun setPassword(password: String)
}