package com.frn.meditradenttokenauth.di

import android.content.Context
import com.frn.meditradenttokenauth.data.remote.MeditradentApi
import com.frn.meditradenttokenauth.data.remote.RemoteDataSource
import com.frn.meditradenttokenauth.data.repository.AuthRepositoryIml
import com.frn.meditradenttokenauth.data.repository.DataStoreOperationsImpl
import com.frn.meditradenttokenauth.data.repository.UserRepositoryIml
import com.frn.meditradenttokenauth.domain.repository.AuthRepository
import com.frn.meditradenttokenauth.domain.repository.DataStoreOperations
import com.frn.meditradenttokenauth.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource()
    }

    @Singleton
    @Provides
    fun provideAuthApi(
        remoteDataSource: RemoteDataSource,
        @ApplicationContext context: Context
    ): MeditradentApi {
        return remoteDataSource.buildApi(MeditradentApi::class.java, context)
    }

    @Singleton
    @Provides
    fun provideUserPreferences(@ApplicationContext context: Context): DataStoreOperations {
        return DataStoreOperationsImpl(context)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(
        authApi: MeditradentApi,
        userPreferences: DataStoreOperations
    ): AuthRepository {
        return AuthRepositoryIml(authApi, userPreferences)
    }

    @Provides
    @Singleton
    fun provideUserRepository(userApi: MeditradentApi): UserRepository {
        return UserRepositoryIml(userApi)
    }


}