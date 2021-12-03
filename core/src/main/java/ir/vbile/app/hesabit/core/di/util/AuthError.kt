package ir.vbile.app.hesabit.core.di.util

sealed class AuthError : Error() {
    object FieldEmpty : AuthError()
    object InputTooShort : AuthError()
    object InvalidEmail : AuthError()
    object InvalidPassword : AuthError()
    object InvalidConfirmPassword : AuthError()
}
