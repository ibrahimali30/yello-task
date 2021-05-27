package com.ibrahim.emitter.users.domain.interactor


import com.ibrahim.emitter.users.data.model.UsersResponse
import com.ibrahim.emitter.users.domain.repsitory.UsersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val wordsRepository: UsersRepository) {

    fun fetchUsers(): Single<UsersResponse> {
        return wordsRepository.fetchUsers()
    }


}