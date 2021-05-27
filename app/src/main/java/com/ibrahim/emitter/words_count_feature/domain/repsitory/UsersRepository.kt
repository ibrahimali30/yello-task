package com.ibrahim.emitter.words_count_feature.domain.repsitory

import com.ibrahim.emitter.words_count_feature.data.model.UsersResponse
import io.reactivex.Single

interface UsersRepository {

    fun fetchUsers(): Single<UsersResponse>

}