package ir.vbile.app.hesabit.feature_authentication.presentation

import ir.vbile.app.hesabit.core.di.util.ValidationError
import ir.vbile.app.hesabit.core.di.util.Resource
import ir.vbile.app.hesabit.feature_authentication.data.models.ResponseAuthentication

data class AuthenticationResult(
    val emailError: ValidationError? = null,
    val passwordError: ValidationError? = null,
    val result: Resource<ResponseAuthentication>? = null
)