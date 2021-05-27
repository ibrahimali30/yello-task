package com.ibrahim.emitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.emitter.words_count_feature.data.model.UsersResponseItem
import com.ibrahim.emitter.words_count_feature.presentation.adapter.UsersAdapter
import com.ibrahim.emitter.words_count_feature.presentation.viewmodel.UsersViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error_view.view.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var wordsViewModel: UsersViewModel

    val adapter: UsersAdapter by lazy { UsersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordsViewModel.getUsers()

        observeScreenState()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        rvUsers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvUsers.adapter = adapter
    }

    private fun observeScreenState() {
        wordsViewModel.screenState.observe(this , Observer {
            onScreenStateChanged(it)
        })
    }

    private fun onScreenStateChanged(state: UsersViewModel.UsersScreenState?) {
        handleErrorViewsVisibility(state is UsersViewModel.UsersScreenState.ErrorLoadingFromApi)
        handleLoadingVisibility(state == UsersViewModel.UsersScreenState.Loading)

        when (state) {
            is UsersViewModel.UsersScreenState.SuccessAPIResponse -> handleSuccess(state.data)
            is UsersViewModel.UsersScreenState.ErrorLoadingFromApi -> handleError(state.error)

            else -> {}
        }
    }

    private fun handleErrorViewsVisibility(show: Boolean) {
        errorViewLayout.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun handleError(error: Throwable) {
        errorViewLayout.btRetry.setOnClickListener {
            wordsViewModel.getUsers()
        }
    }

    private fun handleLoadingVisibility(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }


    private fun handleSuccess(data: List<UsersResponseItem>) {
        adapter.setList(data)
    }

}