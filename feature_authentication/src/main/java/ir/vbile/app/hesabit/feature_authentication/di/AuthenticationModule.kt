package ir.vbile.app.hesabit.feature_authentication.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.vbile.app.hesabit.core.BuildConfig
import ir.vbile.app.hesabit.feature_authentication.data.repository.AuthenticationRepositoryImpl
import ir.vbile.app.hesabit.feature_authentication.data.repository.DemoAuthenticationRepositoryImpl
import ir.vbile.app.hesabit.feature_authentication.domain.repository.AuthenticationRepository
import ir.vbile.app.hesabit.feature_authentication.domain.use_case.SignInUseCase
import ir.vbile.app.hesabit.feature_authentication.domain.use_case.AuthenticateUseCases
import ir.vbile.app.hesabit.feature_authentication.domain.use_case.ResetPasswordUseCase
import ir.vbile.app.hesabit.feature_authentication.domain.use_case.SignUpUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationModule {

    @Provides
    @Singleton
    fun provideAuthenticationRepository(
        impl: AuthenticationRepositoryImpl
    ): AuthenticationRepository =
        if (!BuildConfig.DEMO_MODE) impl else DemoAuthenticationRepositoryImpl()

    @Provides
    @Singleton
    fun provideSignInUseCase(
        repository: AuthenticationRepository
    ) = SignInUseCase(repository)

    @Provides
    @Singleton
    fun provideSignUpUseCase(
        repository: AuthenticationRepository
    ) = SignUpUseCase(repository)

    @Provides
    @Singleton
    fun provideResetPasswordUseCase(
        repository: AuthenticationRepository
    ) = ResetPasswordUseCase(repository)

    @Provides
    @Singleton
    fun provideAuthenticateUseCases(
        signUpUseCase: SignUpUseCase,
        signInUseCase: SignInUseCase,
        resetPasswordUseCase: ResetPasswordUseCase
    ) = AuthenticateUseCases(
        signIn = signInUseCase,
        signUp = signUpUseCase,
        resetPassword = resetPasswordUseCase
    )
}