package ir.vbile.app.hesabit.feature_authentication.domain.repository

import ir.vbile.app.hesabit.core.di.util.Resource
import ir.vbile.app.hesabit.feature_authentication.data.models.ResponseAuthentication
import ir.vbile.app.hesabit.feature_authentication.data.models.ResponseResetPassword

interface AuthenticationRepository {
    suspend fun signUp(
        emailAddress: String,
        password: String
    ): Resource<ResponseAuthentication>

    suspend fun signIn(
        emailAddress: String,
        password: String
    ):  Resource<ResponseAuthentication>

    suspend fun resetPassword(
        emailAddress: String
    ): Resource<ResponseResetPassword>
}