package com.ibrahim.emitter.users.data.source.remote

import com.ibrahim.emitter.users.data.model.UsersResponse
import io.reactivex.Single
import retrofit2.http.GET

interface UsersApiService {

    @GET("users")
    fun getUsers(): Single<UsersResponse>

}