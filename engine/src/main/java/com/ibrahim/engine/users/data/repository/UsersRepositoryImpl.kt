package com.ibrahim.engine.users.data.repository

import com.ibrahim.engine.users.data.model.UserUiModel
import com.ibrahim.engine.users.data.model.UsersResponse
import com.ibrahim.engine.users.data.source.local.UsersLocalDataSource
import com.ibrahim.engine.users.data.source.remote.UsersRemoteDataSource
import com.ibrahim.engine.users.domain.mapper.mapToUserUiModel
import com.ibrahim.engine.users.domain.mapper.mapToUserUiModelList
import com.ibrahim.engine.users.domain.repsitory.UsersRepository
import io.reactivex.Single
import javax.inject.Inject


class UsersRepositoryImpl @Inject constructor(
    private val wordsRemoteDataSource: UsersRemoteDataSource,
    private val usersLocalDataSource: UsersLocalDataSource,
) : UsersRepository {

    override fun fetchUsers(): Single<List<UserUiModel>> {
        return wordsRemoteDataSource.fetchUsers().map {
            it.mapToUserUiModelList()
        }
    }

    override fun getUsersFromLocalDB(): Single<List<UserUiModel>> {
        return usersLocalDataSource.getUsersFromLocalDB()
    }

    override fun insertUser(user: UserUiModel): Single<Long> {
        return usersLocalDataSource.insertUser(user)
    }

}
