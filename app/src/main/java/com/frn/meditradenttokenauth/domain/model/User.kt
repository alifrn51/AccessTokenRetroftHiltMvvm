package com.frn.meditradent.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String = "",
    val first_name: String = "",
    val last_name: String = "",
    val pk: Int = -1,
    val username: String = "",
    val name: String = "",
    val phone_number: String = "",
    val type: String = ""
)