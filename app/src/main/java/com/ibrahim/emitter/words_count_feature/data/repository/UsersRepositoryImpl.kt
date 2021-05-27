package com.ibrahim.emitter.words_count_feature.data.repository

import com.ibrahim.emitter.words_count_feature.data.model.UsersResponse
import com.ibrahim.emitter.words_count_feature.data.source.remote.UsersRemoteDataSource
import com.ibrahim.emitter.words_count_feature.domain.repsitory.UsersRepository
import io.reactivex.Single
import javax.inject.Inject


class UsersRepositoryImpl @Inject constructor(
    private val wordsRemoteDataSource: UsersRemoteDataSource
) : UsersRepository {

    override fun fetchUsers(): Single<UsersResponse> {
        return wordsRemoteDataSource.fetchUsers()
    }

}
