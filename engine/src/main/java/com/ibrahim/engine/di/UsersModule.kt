package com.ibrahim.engine.di


import com.ibrahim.engine.data.repository.UsersRepositoryImpl
import com.ibrahim.engine.data.source.remote.UsersApiService
import com.ibrahim.engine.domain.repsitory.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

@Module
@InstallIn(ActivityComponent::class)
class UsersModule {


    @Provides
    fun providesUsersRepository(wordsRepositoryImpl: UsersRepositoryImpl): UsersRepository {
        return wordsRepositoryImpl
    }

    @Provides
    fun providesUsersApiService(builder: Retrofit.Builder): UsersApiService {
        return builder.build().create(UsersApiService::class.java)
    }

}