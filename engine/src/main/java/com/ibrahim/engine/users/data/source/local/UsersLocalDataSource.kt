package com.ibrahim.engine.users.data.source.local

import android.annotation.SuppressLint
import com.ibrahim.engine.users.data.model.UserUiModel
import io.reactivex.Single
import javax.inject.Inject


class UsersLocalDataSource @Inject constructor(
    private val wordsDao: UserDao
) {

    fun getUsersFromLocalDB(): Single<List<UserUiModel>> {
        return Single.fromCallable {
            wordsDao.getUsersFromLocalDB()
        }
    }

    @SuppressLint("CheckResult")
    fun insertUser(user: UserUiModel): Single<Long> {
        return wordsDao.insertUser(user)
    }

}