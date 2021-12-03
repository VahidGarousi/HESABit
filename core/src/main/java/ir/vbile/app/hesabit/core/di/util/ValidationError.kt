package ir.vbile.app.hesabit.core.di.util

sealed class ValidationError : Error() {
    object FieldEmpty : ValidationError()
    object InputTooShort : ValidationError()
    object InvalidEmail : ValidationError()
    object InvalidPassword : ValidationError()
    object InvalidConfirmPassword : ValidationError()
}
