package ir.vbile.app.hesabit.feature_authentication.presentation.reset_password

import ir.vbile.app.hesabit.core.di.presentation.components.StandardTextFiledState
import ir.vbile.app.hesabit.core.di.util.UiText


class ForgotPasswordState(
    val emailAddress: StandardTextFiledState = StandardTextFiledState(),
    val isLoading: Boolean = false,
    val success: Boolean? = null,
    val errorMessage: UiText? = UiText.unknownError()
) {

    companion object {
        fun initialise(): ForgotPasswordState = ForgotPasswordState()
    }

    fun build(block: Builder.() -> Unit) = Builder(this).apply(block).build()

    class Builder(state: ForgotPasswordState) {
        var userEmail = state.emailAddress
        var loading = state.isLoading
        var success = state.success
        var errorMessage = state.errorMessage

        fun build(): ForgotPasswordState {
            return ForgotPasswordState(
                userEmail,
                loading,
                success,
                errorMessage
            )
        }
    }
}