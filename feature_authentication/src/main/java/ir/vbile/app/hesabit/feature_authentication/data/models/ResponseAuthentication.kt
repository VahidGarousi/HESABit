package ir.vbile.app.hesabit.feature_authentication.data.models

class ResponseAuthentication(
    val token: String? = null,
    val message: String? = null,
    val errorCode: Int? = null
)