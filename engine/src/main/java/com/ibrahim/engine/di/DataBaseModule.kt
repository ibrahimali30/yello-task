package com.ibrahim.engine.di

import android.app.Application
import androidx.room.Room
import com.ibrahim.engine.db.AppDatabase
import com.ibrahim.engine.data.source.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    @Provides
    fun provideLocalDatabase(context: Application): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "AppDatabase")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesWordsDao(WeatherDatabase: AppDatabase): UserDao {
        return WeatherDatabase.userDao()
    }


}