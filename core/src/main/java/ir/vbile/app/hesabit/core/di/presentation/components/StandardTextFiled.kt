package ir.vbile.app.hesabit.core.di.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import ir.vbile.app.hesabit.core.R
import ir.vbile.app.hesabit.core.di.presentation.ui.theme.IconSizeMedium
import ir.vbile.app.hesabit.core.di.presentation.ui.theme.SpaceSmall

@Composable
fun StandardTextFiled(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = stringResource(id = R.string.hint_email_address),
    error: String = "",
    style: TextStyle = TextStyle(
        color = MaterialTheme.colors.onBackground
    ),
    singleLine: Boolean = true,
    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordToggleDisplayed: Boolean = keyboardType == KeyboardType.Password,
    showPasswordToggle: Boolean = false,
    onPasswordToggleClick: (Boolean) -> Unit = {},
    leadingIcon: ImageVector? = null
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            if (hint != "") {
                Text(
                    text = hint,
                    style = MaterialTheme.typography.body1
                )
            }
        },
        maxLines = maxLines,
        textStyle = style,
        singleLine = singleLine,
        isError = error != "",
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        visualTransformation = if (!showPasswordToggle && isPasswordToggleDisplayed) PasswordVisualTransformation() else VisualTransformation.None,
        leadingIcon = if (leadingIcon != null) {
            {
                Icon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    modifier = Modifier.size(IconSizeMedium)
                )
            }
        } else null,
        trailingIcon = if (isPasswordToggleDisplayed) {
            {
                IconButton(onClick = {
                    onPasswordToggleClick(!showPasswordToggle)
                }) {
                    Icon(
                        imageVector = if (showPasswordToggle) {
                            Icons.Filled.VisibilityOff
                        } else {
                            Icons.Filled.Visibility
                        },
                        contentDescription = if (showPasswordToggle) {
                            stringResource(id = R.string.password_visible_content_description)
                        } else stringResource(id = R.string.password_hidden_content_description)
                    )
                }
            }
        } else null,
        modifier = modifier.fillMaxWidth()
    )
    if (error.isNotEmpty()) {
        Text(
            text = error,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.error,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = SpaceSmall)
        )
    }
}