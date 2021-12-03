package ir.vbile.app.hesabit.feature_authentication.presentation.reset_password

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ir.vbile.app.hesabit.core.di.presentation.components.StandardTextFiled
import ir.vbile.app.hesabit.core.di.presentation.ui.theme.SpaceLarge
import ir.vbile.app.hesabit.core.di.presentation.ui.theme.SpaceMedium
import ir.vbile.app.hesabit.feature_authentication.R

@Composable
fun ResetPasswordUI(
    viewModel: ResetPasswordViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    ResetPasswordContent(
        state,
        viewModel::handleRegisterEvent
    )
}

@Composable
private fun ResetPasswordContent(
    state: ForgotPasswordState,
    events: (ResetPasswordEvent) -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize().padding(SpaceLarge),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = MaterialTheme.colors.secondary
            )
        }
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            StandardTextFiled(
                value = state.emailAddress.text,
                onValueChange = {
                    events(ResetPasswordEvent.EmailChanged(it))
                },
                hint = stringResource(id = R.string.hint_forgot_password)
            )
            Spacer(modifier = Modifier.height(SpaceMedium))
            Button(onClick = {
                events(ResetPasswordEvent.AuthenticateClicked)
            }) {
                Text(stringResource(id = R.string.title_forgot_password))
            }
        }
    }
}