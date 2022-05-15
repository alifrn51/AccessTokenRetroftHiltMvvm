package com.frn.meditradenttokenauth.presentation.login

import com.frn.meditradent.domain.model.User
import com.frn.meditradenttokenauth.utils.Resource

data class LoginState(
    val registration:Boolean = false,
    val token:String? = null,
    val isLoading: Boolean = false,
    var error: String? = null,
    var errorHttp:String? = null,
    var user:User? = null
)