package ir.vbile.app.hesabit.feature_authentication.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import ir.vbile.app.hesabit.core.di.presentation.components.StandardTextFiled
import ir.vbile.app.hesabit.core.di.presentation.ui.theme.SpaceLarge
import ir.vbile.app.hesabit.core.di.presentation.ui.theme.SpaceMedium
import ir.vbile.app.hesabit.core.di.presentation.util.ValidationConstants
import ir.vbile.app.hesabit.core.di.util.ValidationError
import ir.vbile.app.hesabit.feature_authentication.R

@Composable
fun Authentication(
    viewModel: AuthenticationViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    Authentication(
        viewState = state,
        events = viewModel::handleAuthenticationEvent
    )
}

@Composable
private fun Authentication(
    viewState: AuthenticationState,
    events: (event: AuthenticationEvent) -> Unit
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        if (viewState.authenticationMode is AuthenticateMode.SignIn) {
            SignIn(viewState, events)
        } else {
            SignUp(viewState, events)
        }
    }
}

@Composable
private fun ForgotPassword(
    events: (event: AuthenticationEvent) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        TextButton(onClick = {
            events(AuthenticationEvent.ForgotPasswordClicked)
        }) {
            Text(text = stringResource(id = R.string.forgotten_your_password))
        }
    }
}

@Composable
private fun SignIn(
    viewState: AuthenticationState,
    events: (event: AuthenticationEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceLarge),
        contentAlignment = Alignment.Center
    ) {
        Column {
            StandardTextFiled(
                viewState.emailAddress.text,
                onValueChange = {
                    events(AuthenticationEvent.EmailChanged(it))
                },
                hint = stringResource(id = R.string.hint_email_address),
                error = when (viewState.emailAddress.error) {
                    is ValidationError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    is ValidationError.InvalidEmail -> stringResource(id = R.string.invalid_email)
                    else -> ""
                }
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextFiled(
                viewState.password.text,
                onValueChange = {
                    events(AuthenticationEvent.PasswordChanged(it))
                },
                keyboardType = KeyboardType.Password,
                hint = stringResource(id = R.string.hint_password),
                error = when (viewState.password.error) {
                    is ValidationError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    is ValidationError.InvalidPassword -> stringResource(id = R.string.invalid_password)
                    is ValidationError.InputTooShort -> stringResource(
                        id = R.string.input_too_short,
                        ValidationConstants.MIN_PASSWORD_LENGTH
                    )
                    else -> ""
                },
                onPasswordToggleClick = {
                    events(AuthenticationEvent.TogglePasswordVisibility)
                },
                showPasswordToggle = viewState.password.isPasswordVisible
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(onClick = {
                    events(AuthenticationEvent.AuthenticateClicked)
                }) {
                    Text(
                        text = stringResource(id = R.string.sign_in)
                    )
                }
                ForgotPassword(events)
            }
        }
        TextButton(
            onClick = {
                events(AuthenticationEvent.AuthenticationModeToggled)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = SpaceLarge)
        ) {
            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.title_sign_up))
                },
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
private fun SignUp(
    viewState: AuthenticationState,
    events: (event: AuthenticationEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(SpaceLarge),
        contentAlignment = Alignment.Center
    ) {
        Column {
            StandardTextFiled(
                viewState.emailAddress.text,
                onValueChange = {
                    events(AuthenticationEvent.EmailChanged(it))
                },
                hint = stringResource(id = R.string.hint_email_address),
                error = when (viewState.emailAddress.error) {
                    is ValidationError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    is ValidationError.InvalidEmail -> stringResource(id = R.string.invalid_email)
                    else -> ""
                }
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextFiled(
                viewState.password.text,
                onValueChange = {
                    events(AuthenticationEvent.PasswordChanged(it))
                },
                hint = stringResource(id = R.string.hint_password),
                error = when (viewState.password.error) {
                    is ValidationError.FieldEmpty -> stringResource(id = R.string.error_field_empty)
                    is ValidationError.InvalidPassword -> stringResource(id = R.string.invalid_password)
                    is ValidationError.InputTooShort -> stringResource(
                        id = R.string.input_too_short,
                        ValidationConstants.MIN_PASSWORD_LENGTH
                    )
                    else -> ""
                },
                keyboardType = KeyboardType.Password,
                onPasswordToggleClick = {
                    events(AuthenticationEvent.TogglePasswordVisibility)
                },
                showPasswordToggle = viewState.password.isPasswordVisible
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(onClick = {
                events(AuthenticationEvent.AuthenticateClicked)
            }) {
                Text(
                    text = stringResource(id = R.string.sign_up)
                )
            }
        }
        TextButton(
            onClick = {
                events(AuthenticationEvent.AuthenticationModeToggled)
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = SpaceLarge)
        ) {
            Text(
                text = buildAnnotatedString {
                    append(stringResource(id = R.string.title_sign_in))
                },
                textAlign = TextAlign.Center
            )
        }
    }
}