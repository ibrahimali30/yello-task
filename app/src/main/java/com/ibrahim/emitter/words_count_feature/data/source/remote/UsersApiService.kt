package com.ibrahim.emitter.words_count_feature.data.source.remote

import com.ibrahim.emitter.words_count_feature.data.model.UsersResponse
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET

interface UsersApiService {

    @GET("users")
    fun getUsers(): Single<UsersResponse>

}