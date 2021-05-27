package com.ibrahim.engine.users.domain.repsitory

import com.ibrahim.engine.users.data.model.UsersResponse
import io.reactivex.Single

interface UsersRepository {

    fun fetchUsers(): Single<UsersResponse>

}