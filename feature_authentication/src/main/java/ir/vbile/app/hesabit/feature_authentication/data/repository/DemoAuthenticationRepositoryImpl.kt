package ir.vbile.app.hesabit.feature_authentication.data.repository

import ir.vbile.app.hesabit.core.di.util.Resource
import ir.vbile.app.hesabit.feature_authentication.data.models.ResponseAuthentication
import ir.vbile.app.hesabit.feature_authentication.data.models.ResponseResetPassword
import ir.vbile.app.hesabit.feature_authentication.domain.repository.AuthenticationRepository

class DemoAuthenticationRepositoryImpl : AuthenticationRepository {
    override suspend fun signUp(
        emailAddress: String,
        password: String
    ): Resource<ResponseAuthentication> {
        return Resource.Success(ResponseAuthentication())
    }

    override suspend fun signIn(
        emailAddress: String,
        password: String
    ): Resource<ResponseAuthentication> {
        return Resource.Success(ResponseAuthentication())
    }

    override suspend fun resetPassword(
        emailAddress: String
    ): Resource<ResponseResetPassword> {
        return Resource.Success(ResponseResetPassword())
    }
}