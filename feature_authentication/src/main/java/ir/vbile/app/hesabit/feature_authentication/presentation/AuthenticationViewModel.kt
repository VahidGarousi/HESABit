package ir.vbile.app.hesabit.feature_authentication.presentation

import androidx.compose.ui.platform.textInputServiceFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.vbile.app.hesabit.core.di.presentation.components.PasswordTextFieldState
import ir.vbile.app.hesabit.core.di.presentation.components.StandardTextFiledState
import ir.vbile.app.hesabit.core.di.util.Resource
import ir.vbile.app.hesabit.core.di.util.UiText
import ir.vbile.app.hesabit.feature_authentication.data.models.AuthenticationModel
import ir.vbile.app.hesabit.feature_authentication.domain.use_case.AuthenticateUseCases
import ir.vbile.app.hesabit.feature_authentication.domain.use_case.SignInUseCase
import ir.vbile.app.hesabit.feature_authentication.domain.use_case.SignUpUseCase
import ir.vbile.app.hesabit.navigation.NavigationManager
import ir.vbile.app.hesabit.navigation.directions.AuthenticationDirections
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticator: AuthenticateUseCases,
    private val navigationManager: NavigationManager
) : ViewModel(), AuthenticateView {
    private val _uiState = MutableStateFlow(AuthenticationState())
    val uiState: StateFlow<AuthenticationState> = _uiState

    fun handleAuthenticationEvent(event: AuthenticationEvent) {
        if (event is AuthenticationEvent.AuthenticateClicked) {
            _uiState.value = uiState.value.build {
                loading = true
            }
            authenticate()
        } else {
            _uiState.value = _uiState.value.build {
                when (event) {
                    AuthenticationEvent.AuthenticateClicked -> {
                        this.loading = true
                    }
                    AuthenticationEvent.AuthenticationModeToggled -> {
                        if (this.mode is AuthenticateMode.SignIn) {
                            this.mode = AuthenticateMode.SignUp
                        } else {
                            this.mode = AuthenticateMode.SignIn
                        }
                    }
                    AuthenticationEvent.DismissErrorDialog -> {
                        this.error = null
                    }
                    is AuthenticationEvent.EmailChanged -> {
                        this.userEmail = StandardTextFiledState(text = event.email)
                    }
                    AuthenticationEvent.ForgotPasswordClicked -> {
                        navigationManager.navigate(
                            AuthenticationDirections.forgotPassword
                        )
                    }
                    is AuthenticationEvent.PasswordChanged -> {
                        this.userPassword = PasswordTextFieldState(text = event.password)
                    }
                    AuthenticationEvent.TogglePasswordVisibility -> {
                        userPassword = userPassword.copy(isPasswordVisible = !userPassword.isPasswordVisible)
                    }
                }
            }
        }
    }


    override fun authenticate() {
        if (uiState.value.authenticationMode is AuthenticateMode.SignIn) {
            signIn()
        } else {
            signUp()
        }
    }

    override fun signUp() {
        _uiState.value = uiState.value.build {
            loading = true
            error = null
        }
        viewModelScope.launch {
            authenticator.signUp(
                SignUpUseCase.Params.signUp(
                    uiState.value.emailAddress.text,
                    uiState.value.password.text
                )
            ) { result ->
                if (result.emailError != null) {
                    _uiState.value = _uiState.value.build {
                        this.userEmail = userEmail.copy(error = result.emailError)
                    }
                }
                if (result.passwordError != null) {
                    _uiState.value = _uiState.value.build {
                        this.userPassword = userPassword.copy(error = result.passwordError)
                    }
                }
                when (result.result) {
                    is Resource.Error -> {
                        _uiState.value = _uiState.value.build {
                            this.error = result.result.uiText ?: UiText.unknownError()
                        }
                    }
                    is Resource.Success -> {
                        handleResult(result.result.data)
                    }
                }
            }
        }
    }

    override fun signIn() {
        _uiState.value = uiState.value.build {
            loading = true
            error = null
        }
        viewModelScope.launch {
            authenticator.signIn(
                SignInUseCase.Params.signIn(
                    uiState.value.emailAddress.text,
                    uiState.value.password.text
                )
            ) { result ->
                if (result.emailError != null) {
                    _uiState.value = _uiState.value.build {
                        userEmail = userEmail.copy(error = result.emailError)
                    }
                }
                if (result.passwordError != null) {
                    _uiState.value = _uiState.value.build {
                        userPassword = userPassword.copy(error = result.passwordError)
                    }
                }
                when (result.result) {
                    is Resource.Error -> {
                        _uiState.value = _uiState.value.build {
                            error = result.result.uiText ?: UiText.unknownError()
                        }
                    }
                    is Resource.Success -> {
                        handleResult(result.result.data)
                    }
                }
            }
        }
    }

    private fun handleResult(result: AuthenticationModel?) {
        navigationManager.navigate(AuthenticationDirections.dashboard)
    }

    override fun toggleAuthenticationMode() {
        if (uiState.value.authenticationMode == AuthenticateMode.SignIn) {
            _uiState.value = uiState.value.build {
                mode = AuthenticateMode.SignUp
            }
        } else {
            _uiState.value = uiState.value.build {
                mode = AuthenticateMode.SignIn
            }
        }
    }

    override fun setEmailAddress(emailAddress: String) {
        _uiState.value = uiState.value.build {
            this.userEmail = StandardTextFiledState(text = emailAddress)
        }
    }

    override fun setPassword(password: String) {
        _uiState.value = uiState.value.build {
            this.userPassword = PasswordTextFieldState(text = password)
        }
    }
}