package ir.vbile.app.hesabit.core.di.presentation.util

import androidx.core.util.PatternsCompat
import ir.vbile.app.hesabit.core.di.util.ValidationError

object ValidationUtil {

    fun validateEmail(email: String): ValidationError? {
        val trimmedEmail = email.trim()
        if (trimmedEmail.isBlank()) {
            return ValidationError.FieldEmpty
        }
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(trimmedEmail).matches()) {
            return ValidationError.InvalidEmail
        }
        return null
    }

    fun validatePassword(password: String): ValidationError? {
        if (password.isBlank()) {
            return ValidationError.FieldEmpty
        }
        if (password.length < ValidationConstants.MIN_PASSWORD_LENGTH) {
            return ValidationError.InputTooShort
        }
        val capitalLettersInPassword = password.any { it.isUpperCase() }
        val numberInPassword = password.any { it.isDigit() }
        if (!capitalLettersInPassword || !numberInPassword) {
            return ValidationError.InvalidPassword
        }
        return null
    }
    fun validateConfirmPassword(confirmedPassword: String): ValidationError? {
        if (confirmedPassword.isBlank()) {
            return ValidationError.FieldEmpty
        }
        if (confirmedPassword.length < ValidationConstants.MIN_PASSWORD_LENGTH) {
            return ValidationError.InputTooShort
        }
        val capitalLettersInPassword = confirmedPassword.any { it.isUpperCase() }
        val numberInPassword = confirmedPassword.any { it.isDigit() }
        if (!capitalLettersInPassword || !numberInPassword) {
            return ValidationError.InvalidConfirmPassword
        }
        return null
    }
}