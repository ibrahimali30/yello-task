package com.ibrahim.emitter.users.domain.repsitory

import com.ibrahim.emitter.users.data.model.UsersResponse
import io.reactivex.Single

interface UsersRepository {

    fun fetchUsers(): Single<UsersResponse>

}