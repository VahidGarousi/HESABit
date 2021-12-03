package ir.vbile.app.hesabit.core.di.presentation.util

import androidx.core.util.PatternsCompat
import ir.vbile.app.hesabit.core.di.util.AuthError

object ValidationUtil {

    fun validateEmail(email: String): AuthError? {
        val trimmedEmail = email.trim()
        if (trimmedEmail.isBlank()) {
            return AuthError.FieldEmpty
        }
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(trimmedEmail).matches()) {
            return AuthError.InvalidEmail
        }
        return null
    }

    fun validatePassword(password: String): AuthError? {
        if (password.isBlank()) {
            return AuthError.FieldEmpty
        }
        if (password.length < ValidationConstants.MIN_PASSWORD_LENGTH) {
            return AuthError.InputTooShort
        }
        val capitalLettersInPassword = password.any { it.isUpperCase() }
        val numberInPassword = password.any { it.isDigit() }
        if (!capitalLettersInPassword || !numberInPassword) {
            return AuthError.InvalidPassword
        }
        return null
    }
    fun validateConfirmPassword(confirmedPassword: String): AuthError? {
        if (confirmedPassword.isBlank()) {
            return AuthError.FieldEmpty
        }
        if (confirmedPassword.length < ValidationConstants.MIN_PASSWORD_LENGTH) {
            return AuthError.InputTooShort
        }
        val capitalLettersInPassword = confirmedPassword.any { it.isUpperCase() }
        val numberInPassword = confirmedPassword.any { it.isDigit() }
        if (!capitalLettersInPassword || !numberInPassword) {
            return AuthError.InvalidConfirmPassword
        }
        return null
    }
}