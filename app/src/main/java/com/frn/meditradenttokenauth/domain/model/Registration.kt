package com.frn.meditradent.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Registration(
    val access_token: String  = "",
    val refresh_token: String  = "",
    val user: User = User()
)