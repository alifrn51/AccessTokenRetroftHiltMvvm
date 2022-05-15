package com.frn.meditradenttokenauth.data.repository

import com.frn.meditradenttokenauth.core.SafeApiCall
import com.frn.meditradenttokenauth.data.remote.MeditradentApi
import com.frn.meditradenttokenauth.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryIml @Inject constructor(
    private val api: MeditradentApi
) : UserRepository, SafeApiCall {

    override suspend fun getUser() = safeApiCall { api.getUser() }

}