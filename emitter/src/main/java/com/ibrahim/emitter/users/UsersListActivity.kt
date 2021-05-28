package com.ibrahim.emitter.users

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.emitter.R
import com.ibrahim.engine.base.EXTRA_USER
import com.ibrahim.engine.base.MIDDLE_MAN_PACKAGE_NAME
import com.ibrahim.engine.base.MIDDLE_MAN_RECEIVER_NAME
import com.ibrahim.engine.users.data.model.UserUiModel
import com.ibrahim.emitter.users.adapter.UsersAdapter
import com.ibrahim.engine.base.MIDDLE_MAN_RECEIVER_PACKAGE_NAME
import com.ibrahim.engine.users.domain.mapper.toJson
import com.ibrahim.engine.users.presentation.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error_view.view.*
import javax.inject.Inject


@AndroidEntryPoint
class UsersListActivity : AppCompatActivity() {

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

    private fun onUserClicked(user: UserUiModel){
        setBroadCastToMiddleMan(user)
        openReceiverActivity()
    }

    private fun openReceiverActivity() {
        val intent = Intent()
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        intent.component =
            ComponentName(
                "com.ibrahim.receiver", "com.ibrahim.receiver.ReceiverMainActivity"
            )
        startActivity(intent)
    }

    private fun setBroadCastToMiddleMan(user: UserUiModel) {
        val intent = Intent(MIDDLE_MAN_RECEIVER_NAME)
        intent.action = MIDDLE_MAN_PACKAGE_NAME
        intent.putExtra(EXTRA_USER, user.toJson())
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        intent.component =
            ComponentName(
                MIDDLE_MAN_PACKAGE_NAME, MIDDLE_MAN_RECEIVER_PACKAGE_NAME
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


    private fun handleSuccess(data: List<UserUiModel>) {
        adapter.setList(data)
    }

}