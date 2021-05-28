package com.ibrahim.engine.data.source.remote

import com.ibrahim.engine.data.model.UsersResponse
import io.reactivex.Single
import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(
    private val wordsApiService: UsersApiService
) {

     fun fetchUsers():Single<UsersResponse> {
       return wordsApiService.getUsers()
     }

}