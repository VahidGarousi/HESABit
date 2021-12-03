package ir.vbile.app.hesabit.feature_authentication.domain.use_case

import javax.inject.Inject

class AuthenticateUseCases @Inject constructor(
    val signUp: SignUpUseCase,
    val signIn: SignInUseCase,
)