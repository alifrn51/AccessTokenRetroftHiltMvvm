package com.frn.meditradenttokenauth.domain.model

data class TokenResponse(
    val access_token: String?,
    val refresh_token: String?
)