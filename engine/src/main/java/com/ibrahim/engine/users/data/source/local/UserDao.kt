package com.ibrahim.engine.users.data.source.local

import androidx.room.*
import com.ibrahim.engine.users.data.model.UserUiModel
import io.reactivex.Single


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(words: UserUiModel): Single<Long>

    @Query("select * from UserUiModel")
    fun getUsersFromLocalDB(): List<UserUiModel>

}