package ir.vbile.app.hesabit.feature_authentication.domain.repository

import ir.vbile.app.hesabit.core.di.util.Resource
import ir.vbile.app.hesabit.core.di.util.SimpleResource
import ir.vbile.app.hesabit.feature_authentication.data.models.AuthenticationModel

interface AuthenticationRepository {
    suspend fun signUp(
        emailAddress: String,
        password: String
    ): Resource<AuthenticationModel>

    suspend fun signIn(
        emailAddress: String,
        password: String
    ):  Resource<AuthenticationModel>
}