package ir.vbile.app.hesabit.feature_authentication.data.repository

import ir.vbile.app.hesabit.core.di.util.Resource
import ir.vbile.app.hesabit.feature_authentication.data.models.AuthenticationModel
import ir.vbile.app.hesabit.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(

) : AuthenticationRepository {
    override suspend fun signUp(
        emailAddress: String,
        password: String
    ): Resource<AuthenticationModel> {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(
        emailAddress: String,
        password: String
    ): Resource<AuthenticationModel> {
        TODO("Not yet implemented")
    }
}