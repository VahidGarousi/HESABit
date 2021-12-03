package ir.vbile.app.hesabit.feature_authentication.data.models

import ir.vbile.app.hesabit.core.di.util.AuthError
import ir.vbile.app.hesabit.core.di.util.Resource

data class AuthenticationResult(
    val emailError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: Resource<AuthenticationModel>? = null
)