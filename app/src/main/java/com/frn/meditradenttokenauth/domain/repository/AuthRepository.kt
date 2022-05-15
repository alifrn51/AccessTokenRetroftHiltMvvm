package com.frn.meditradenttokenauth.domain.repository

import com.frn.meditradent.domain.model.Registration
import com.frn.meditradenttokenauth.utils.Resource

interface AuthRepository {

    suspend fun login(
        username :String,
        password: String
    ): Resource<Registration>


    suspend fun signup(
        username :String,
        password: String,
        rePassword: String
    ): Resource<Registration>

    suspend fun saveAccessTokens(accessToken: String, refreshToken: String)

}