package com.ibrahim.engine.domain.repsitory

import com.ibrahim.engine.data.model.UserUiModel
import io.reactivex.Single

interface UsersRepository {

    fun fetchUsers(): Single<List<UserUiModel>>
    fun getUsersFromLocalDB(): Single<List<UserUiModel>>
    fun insertUser(user: UserUiModel): Single<Long>

}