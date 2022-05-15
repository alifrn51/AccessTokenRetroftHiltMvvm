package com.frn.meditradenttokenauth.data.remote

import com.frn.meditradent.domain.model.Registration
import com.frn.meditradent.domain.model.User
import com.frn.meditradenttokenauth.domain.model.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MeditradentApi {

    @FormUrlEncoded
    @POST("/api/accounts/login/")
    suspend fun login(
        @Field("email") email:String,
        @Field("password") password: String
    ): Registration


    @FormUrlEncoded
    @POST("/api/accounts/register/")
    suspend fun signup(
        @Field("email") userName: String,
        @Field("password1") password1: String,
        @Field("password2") password2: String
    ): Registration


    @FormUrlEncoded
    @POST("/api/accounts/token/refresh/")
    suspend fun refreshAccessToken(
        @Field("refresh_token") refreshToken: String?
    ): Registration

    @GET("/api/users/")
    suspend fun getUser(): List<User>

}