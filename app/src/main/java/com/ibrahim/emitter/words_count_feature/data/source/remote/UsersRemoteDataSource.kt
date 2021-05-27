package com.ibrahim.emitter.words_count_feature.data.source.remote

import com.ibrahim.emitter.words_count_feature.data.model.UsersResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import javax.inject.Inject

class UsersRemoteDataSource @Inject constructor(
    private val wordsApiService: UsersApiService
) {

     fun fetchUsers():Single<UsersResponse> {
       return wordsApiService.getUsers()
     }

}