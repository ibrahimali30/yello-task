package com.ibrahim.engine.users.data.source.remote

import com.ibrahim.engine.users.data.model.UsersResponse
import io.reactivex.Single
import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(
    private val wordsApiService: UsersApiService
) {

     fun fetchUsers():Single<UsersResponse> {
       return wordsApiService.getUsers()
     }

}