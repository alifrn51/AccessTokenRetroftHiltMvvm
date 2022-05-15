package com.frn.meditradenttokenauth.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {

    suspend fun saveOnBoardingState(completed: Boolean)

    fun readOnBoardingState(): Flow<Boolean>

    suspend fun saveToken(accessToken: String , refreshToken: String)

    fun readToken():Flow<String>
    fun readRefreshToken():Flow<String>
}