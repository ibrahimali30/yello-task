package com.ibrahim.emitter.words_count_feature.domain.interactor


import com.ibrahim.emitter.words_count_feature.data.model.UsersResponse
import com.ibrahim.emitter.words_count_feature.domain.repsitory.UsersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val wordsRepository: UsersRepository) {

    fun fetchUsers(): Single<UsersResponse> {
        return wordsRepository.fetchUsers()
    }


}