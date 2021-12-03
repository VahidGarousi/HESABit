package ir.vbile.app.hesabit.feature_authentication.presentation.reset_password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.hesabit.core.di.util.Resource
import ir.vbile.app.hesabit.core.di.util.UiText
import ir.vbile.app.hesabit.feature_authentication.data.models.ResponseResetPassword
import ir.vbile.app.hesabit.feature_authentication.domain.use_case.AuthenticateUseCases
import ir.vbile.app.hesabit.feature_authentication.domain.use_case.ResetPasswordUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResetPasswordViewModel @Inject constructor(
    private val authenticate: AuthenticateUseCases
) : ViewModel(), ResetPasswordView {
    private val _uiState = MutableStateFlow(ForgotPasswordState())
    val uiState: StateFlow<ForgotPasswordState> = _uiState

    override fun resetPassword(emailAddress: String) {
        viewModelScope.launch {
            authenticate.resetPassword(
                ResetPasswordUseCase.Params.resetPassword(
                    uiState.value.emailAddress.text
                )
            ) { result ->
                if (result.emailError != null) {
                    _uiState.value = _uiState.value.build {
                        this.userEmail = this.userEmail.copy(error = result.emailError)
                    }
                }
                when (result.result) {
                    is Resource.Error -> {
                        _uiState.value = _uiState.value.build {
                            errorMessage = result.result.uiText ?: UiText.unknownError()
                        }
                    }
                    is Resource.Success -> {
                        handleResult(result.result.data)
                    }
                }
            }
        }
    }

    private fun handleResult(result: ResponseResetPassword?) {
        // TODO: 12/3/2021
    }

    fun handleRegisterEvent(event: ResetPasswordEvent) {
        when (event) {
            ResetPasswordEvent.AuthenticateClicked -> {
                _uiState.value = _uiState.value.build {

                }
            }
            is ResetPasswordEvent.EmailChanged -> {
                _uiState.value = _uiState.value.build {
                    userEmail = userEmail.copy(text = event.email)
                }
            }
        }
    }
}