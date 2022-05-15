package com.frn.meditradenttokenauth.data.repository

import com.frn.meditradent.domain.model.Registration
import com.frn.meditradenttokenauth.core.SafeApiCall
import com.frn.meditradenttokenauth.data.remote.MeditradentApi
import com.frn.meditradenttokenauth.utils.Resource
import com.frn.meditradenttokenauth.domain.repository.AuthRepository
import com.frn.meditradenttokenauth.domain.repository.DataStoreOperations
import javax.inject.Inject

class AuthRepositoryIml @Inject constructor(
    private val api: MeditradentApi,
    private val preferences: DataStoreOperations
) : AuthRepository , SafeApiCall {

    override suspend fun login(
        username: String,
        password: String
    ) = safeApiCall {
        api.login(username, password)
    }

    override suspend fun signup(
        username: String,
        password: String,
        rePassword: String
    ): Resource<Registration> {
        TODO("Not yet implemented")
    }

    override suspend fun saveAccessTokens(accessToken: String, refreshToken: String) {
        preferences.saveToken(accessToken, refreshToken)
    }

}