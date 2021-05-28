package com.ibrahim.engine.domain.interactor


import com.ibrahim.engine.data.model.UserUiModel
import com.ibrahim.engine.domain.repsitory.UsersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val wordsRepository: UsersRepository) {

    fun fetchUsers(): Single<List<UserUiModel>> {
        return wordsRepository.fetchUsers()
    }


}