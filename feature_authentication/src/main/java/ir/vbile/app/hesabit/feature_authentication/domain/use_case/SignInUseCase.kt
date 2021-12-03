package ir.vbile.app.hesabit.feature_authentication.domain.use_case

import ir.vbile.app.hesabit.core.di.presentation.util.ValidationUtil
import ir.vbile.app.hesabit.feature_authentication.presentation.AuthenticationResult
import ir.vbile.app.hesabit.feature_authentication.domain.repository.AuthenticationRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthenticationRepository
) {
    suspend operator fun invoke(
        params: Params,
        completion: (AuthenticationResult) -> Unit
    ) {
        val trimmedEmail = params.emailAddress.trim()
        val trimmedPassword = params.password.trim()
        val emailError = ValidationUtil.validateEmail(trimmedEmail)
        val passwordError = ValidationUtil.validatePassword(trimmedPassword)
        if (emailError != null || passwordError != null) {
            val result = AuthenticationResult(emailError, passwordError)
            completion(result)
            return
        }
        val result = repository.signIn(params.emailAddress, params.password)
        completion(AuthenticationResult(result = result))
    }

    open class Params private constructor(
        val emailAddress: String,
        val password: String
    ) {
        companion object {
            fun signIn(
                emailAddress: String,
                password: String
            ) = Params(
                emailAddress,
                password
            )
        }
    }
}