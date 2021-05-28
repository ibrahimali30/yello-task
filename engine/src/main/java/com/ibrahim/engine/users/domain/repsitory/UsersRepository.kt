package com.ibrahim.engine.users.domain.repsitory

import com.ibrahim.engine.users.data.model.UserUiModel
import com.ibrahim.engine.users.data.model.UsersResponse
import io.reactivex.Single

interface UsersRepository {

    fun fetchUsers(): Single<List<UserUiModel>>
    fun getUsersFromLocalDB(): Single<List<UserUiModel>>
    fun insertUser(user: UserUiModel): Single<Long>

}