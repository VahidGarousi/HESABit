package ir.vbile.app.hesabit.feature_authentication.utils

import ir.vbile.app.hesabit.core.di.util.Error

sealed class ResetPasswordError : Error() {
    object FieldEmpty : ResetPasswordError()
    object InputTooShort : ResetPasswordError()
    object InvalidEmail : ResetPasswordError()
}
