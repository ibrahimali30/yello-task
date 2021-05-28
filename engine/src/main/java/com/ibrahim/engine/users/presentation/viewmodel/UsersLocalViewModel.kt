package com.ibrahim.engine.users.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.engine.users.data.model.UserUiModel
import com.ibrahim.engine.users.domain.interactor.GetLocalUsersUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class UsersLocalViewModel @Inject constructor(
        private val wordsUseCase: GetLocalUsersUseCase
): ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val savedUsersLiveData by lazy { MutableLiveData<List<UserUiModel>>() }

    fun getUsersFromLocalDB() {
        wordsUseCase.getUsersFromLocalDB()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                savedUsersLiveData.postValue(it)
            }, {

            }).addTo(compositeDisposable)
    }

    fun insertUserInToLocalDB(user: UserUiModel) {
        wordsUseCase.insertUserInToLocalDB(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it
            }, {
                it
            }).addTo(compositeDisposable)
    }



    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

}