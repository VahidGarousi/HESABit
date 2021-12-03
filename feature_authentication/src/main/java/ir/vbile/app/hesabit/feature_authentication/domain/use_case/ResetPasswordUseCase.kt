package ir.vbile.app.hesabit.feature_authentication.domain.use_case

import ir.vbile.app.hesabit.core.di.presentation.util.ValidationUtil
import ir.vbile.app.hesabit.feature_authentication.domain.repository.AuthenticationRepository
import ir.vbile.app.hesabit.feature_authentication.presentation.reset_password.ResetPasswordResult

class ResetPasswordUseCase constructor(
    private val repository: AuthenticationRepository
) {

    suspend operator fun invoke(
        params: Params,
        completion: (ResetPasswordResult) -> Unit
    ) {
        val trimmedEmail = params.emailAddress.trim()
        val emailError = ValidationUtil.validateEmail(trimmedEmail)
        if (emailError != null) {
            completion(ResetPasswordResult(emailError))
            return
        }
        val result = repository.resetPassword(trimmedEmail)
        completion(ResetPasswordResult(result = result))
    }

    open class Params private constructor(
        val emailAddress: String
    ) {
        companion object {
            fun resetPassword(
                emailAddress: String
            ) = Params(
                emailAddress
            )
        }
    }
}