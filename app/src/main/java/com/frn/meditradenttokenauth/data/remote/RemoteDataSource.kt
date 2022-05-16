package com.frn.meditradenttokenauth.data.remote

import android.content.Context
import com.frn.meditradenttokenauth.BuildConfig
import com.frn.meditradenttokenauth.data.repository.DataStoreOperationsImpl
import com.frn.meditradenttokenauth.domain.repository.DataStoreOperations
import com.frn.meditradenttokenauth.utils.Constants.BASE_URL
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RemoteDataSource {


    fun <Api> buildApi(
        api: Class<Api>,
        context: Context
    ): Api {
        val authenticator =
            TokenAuthenticator(buildTokenApi(context), DataStoreOperationsImpl(context))

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getRetrofitClient(authenticator, DataStoreOperationsImpl(context = context)))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(api)
    }

    private fun buildTokenApi(context: Context): MeditradentApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getRetrofitClient(dataStoreOperations = DataStoreOperationsImpl(context = context)))
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MeditradentApi::class.java)
    }

    private fun getRetrofitClient(
        authenticator: Authenticator? = null,
        dataStoreOperations: DataStoreOperations
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                chain.proceed(chain.request().newBuilder().also {
                    runBlocking {
                        val accessToken = dataStoreOperations.readToken().first()
                        if (accessToken.isNotEmpty()) {
                            it.header("Authorization", "Bearer $accessToken")
                        }
                        it.addHeader("Accept", "application/json")
                    }
                }.build())
            }.also { client ->
                authenticator?.let { client.authenticator(it) }
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }.build()
    }
}