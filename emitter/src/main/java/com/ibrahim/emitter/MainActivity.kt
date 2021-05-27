package com.ibrahim.emitter

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.emitter.base.EXTRA_USER
import com.ibrahim.emitter.base.MIDDLE_MAN_PACKAGE_NAME
import com.ibrahim.emitter.base.MIDDLE_MAN_RECEIVER_NAME
import com.ibrahim.emitter.users.data.model.UsersResponseItem
import com.ibrahim.emitter.users.presentation.adapter.UsersAdapter
import com.ibrahim.emitter.users.presentation.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error_view.view.*
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var wordsViewModel: UsersViewModel

    val adapter: UsersAdapter by lazy { UsersAdapter(onItemClicked = ::onUserClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wordsViewModel.getUsers()

        observeScreenState()
        initRecyclerView()

    }

    private fun onUserClicked(user: UsersResponseItem){
        setBroadCastToMiddleMan(user)
    }

    private fun setBroadCastToMiddleMan(user: UsersResponseItem) {
        val intent = Intent(MIDDLE_MAN_RECEIVER_NAME)
        intent.action = MIDDLE_MAN_PACKAGE_NAME
        intent.putExtra(EXTRA_USER, user)
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        intent.component =
            ComponentName(
                MIDDLE_MAN_PACKAGE_NAME,
                "${MIDDLE_MAN_PACKAGE_NAME}.MiddleManProdCastReceiver"
            )
        sendBroadcast(intent)
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