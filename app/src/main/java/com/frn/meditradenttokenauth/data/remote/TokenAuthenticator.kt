package com.frn.meditradenttokenauth.data.remote

import android.content.Context
import com.frn.meditradent.domain.model.Registration
import com.frn.meditradenttokenauth.core.SafeApiCall
import com.frn.meditradenttokenauth.data.repository.DataStoreOperationsImpl
import com.frn.meditradenttokenauth.utils.Resource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class TokenAuthenticator @Inject constructor(
    context: Context,
    private val api: MeditradentApi
) : Authenticator , SafeApiCall {

    private val appContext = context.applicationContext
    private val userPreferences = DataStoreOperationsImpl(appContext)

    override fun authenticate(route: Route?, response: Response): Request? {
        return runBlocking {
            when (val tokenResponse = getUpdatedToken()) {
                is Resource.Success -> {
                    userPreferences.saveToken(
                        tokenResponse.value.access_token,
                        tokenResponse.value.refresh_token
                    )
                    response.request.newBuilder()
                        .header("Authorization", "Bearer ${tokenResponse.value.access_token}")
                        .build()
                }
                else -> null
            }
        }
    }

    private suspend fun getUpdatedToken(): Resource<Registration> {
        val refreshToken = userPreferences.readRefreshToken().first()
        return safeApiCall { api.refreshAccessToken(refreshToken) }
    }

}