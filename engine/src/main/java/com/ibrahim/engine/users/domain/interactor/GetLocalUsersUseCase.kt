package com.ibrahim.engine.users.domain.interactor


import com.ibrahim.engine.users.data.model.UserUiModel
import com.ibrahim.engine.users.data.model.UsersResponse
import com.ibrahim.engine.users.domain.repsitory.UsersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetLocalUsersUseCase @Inject constructor(private val wordsRepository: UsersRepository) {

    fun getUsersFromLocalDB(): Single<List<UserUiModel>> {
        return wordsRepository.getUsersFromLocalDB()
    }

    fun insertUserInToLocalDB(user: UserUiModel): Single<Long> {
        return wordsRepository.insertUser(user)
    }


}