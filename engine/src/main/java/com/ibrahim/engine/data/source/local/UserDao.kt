package com.ibrahim.engine.data.source.local

import androidx.room.*
import com.ibrahim.engine.data.model.UserUiModel
import io.reactivex.Single


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(words: UserUiModel): Single<Long>

    @Query("select * from UserUiModel")
    fun getUsersFromLocalDB(): List<UserUiModel>

}