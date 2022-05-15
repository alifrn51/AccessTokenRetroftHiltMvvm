package com.frn.meditradenttokenauth.domain.repository

import com.frn.meditradent.domain.model.User
import com.frn.meditradenttokenauth.utils.Resource

interface UserRepository {

    suspend fun getUser(): Resource<List<User>>

}