package ir.vbile.app.hesabit.feature_authentication.presentation.reset_password

import ir.vbile.app.hesabit.core.di.util.Resource
import ir.vbile.app.hesabit.core.di.util.ValidationError
import ir.vbile.app.hesabit.feature_authentication.data.models.ResponseResetPassword

data class ResetPasswordResult(
    val emailError: ValidationError? = null,
    val result: Resource<ResponseResetPassword>? = null
)