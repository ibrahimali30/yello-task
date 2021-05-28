package com.ibrahim.engine.data.source.remote

import com.ibrahim.engine.data.model.UsersResponse
import io.reactivex.Single
import retrofit2.http.GET

interface UsersApiService {

    @GET("users")
    fun getUsers(): Single<UsersResponse>

}