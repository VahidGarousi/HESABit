package ir.vbile.app.hesabit.core.di.presentation.components

import ir.vbile.app.hesabit.core.di.util.Error

data class StandardTextFiledState(
    val text: String = "",
    val error: Error? = null
)
