package com.ibrahim.engine.data.repository

import com.ibrahim.engine.data.model.UserUiModel
import com.ibrahim.engine.data.source.local.UsersLocalDataSource
import com.ibrahim.engine.data.source.remote.UsersRemoteDataSource
import com.ibrahim.engine.domain.mapper.mapToUserUiModelList
import com.ibrahim.engine.domain.repsitory.UsersRepository
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
