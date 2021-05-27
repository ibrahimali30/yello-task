package com.ibrahim.emitter.words_count_feature.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.emitter.words_count_feature.data.model.UsersResponse
import com.ibrahim.emitter.words_count_feature.domain.interactor.GetUsersUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class UsersViewModel @Inject constructor(
        private val wordsUseCase: GetUsersUseCase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val screenState by lazy { MutableLiveData<UsersScreenState>() }

    fun getUsers() {
        screenState.value = UsersScreenState.Loading
        wordsUseCase.fetchUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                handleSuccess(it)
            }, {
                handleErrorLoadingFromRemote(it)
            }).addTo(compositeDisposable)
    }

    private fun handleErrorLoadingFromRemote(it: Throwable) {
        screenState.value = UsersScreenState.ErrorLoadingFromApi(it)
    }

    private fun handleSuccess(it: UsersResponse) {
        screenState.value = UsersScreenState.SuccessAPIResponse(it)
    }

    sealed class UsersScreenState {
        object Loading : UsersScreenState()
        class ErrorLoadingFromApi(val error: Throwable) : UsersScreenState()
        class SuccessAPIResponse(val data: UsersResponse) : UsersScreenState()

    }


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}