package com.ibrahim.emitter.users.data.repository

import com.ibrahim.emitter.users.data.model.UsersResponse
import com.ibrahim.emitter.users.data.source.remote.UsersRemoteDataSource
import com.ibrahim.emitter.users.domain.repsitory.UsersRepository
import io.reactivex.Single
import javax.inject.Inject


class UsersRepositoryImpl @Inject constructor(
    private val wordsRemoteDataSource: UsersRemoteDataSource
) : UsersRepository {

    override fun fetchUsers(): Single<UsersResponse> {
        return wordsRemoteDataSource.fetchUsers()
    }

}
