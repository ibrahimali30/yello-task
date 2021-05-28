package com.ibrahim.engine.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ibrahim.engine.users.data.model.UserUiModel
import com.ibrahim.engine.users.data.source.local.UserDao

@Database(
    entities = [
        UserUiModel::class
    ],
    version = 2 , exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}

