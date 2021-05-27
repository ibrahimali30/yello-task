package com.ibrahim.engine.users.data.source.remote

import com.ibrahim.engine.users.data.model.UsersResponse
import io.reactivex.Single
import retrofit2.http.GET

interface UsersApiService {

    @GET("users")
    fun getUsers(): Single<UsersResponse>

}