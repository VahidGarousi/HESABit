package ir.vbile.app.hesabit.feature_authentication.data.models

data class ResponseResetPassword(
    val success: Boolean = false,
    val message: String? = null,
    val errorCode: Int? = null
)
