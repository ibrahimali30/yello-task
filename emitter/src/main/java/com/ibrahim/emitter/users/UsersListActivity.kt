package com.ibrahim.emitter.users

import android.content.*
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.emitter.R
import com.ibrahim.emitter.base.extensions.showNewUserDialog
import com.ibrahim.emitter.adapter.UsersAdapter
import com.ibrahim.engine.base.*
import com.ibrahim.engine.data.model.UserUiModel
import com.ibrahim.engine.domain.mapper.toJson
import com.ibrahim.engine.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_error_view.view.*
import javax.inject.Inject


@AndroidEntryPoint
class UsersListActivity : AppCompatActivity() {

    @Inject
    lateinit var usersViewModel: UsersViewModel
    private val usersAdapter: UsersAdapter by lazy { UsersAdapter(onItemClicked = ::onUserClicked) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usersViewModel.getUsers()
        observeScreenState()
        initRecyclerView()
        uiContext = this
    }

    private fun onUserClicked(user: UserUiModel){
        showNewUserDialog(
            "Send",
            "sending the data to middleMan app ?",
            onConfirmed = {
                sendDataToMiddleMan(user)
            },onCanceled = {}
        )
    }

    private fun sendDataToMiddleMan(user: UserUiModel) {
        sendBroadCastToMiddleMan(user)
        openReceiverActivity()
    }

    private fun openReceiverActivity() {
        val intent = Intent()
        intent.component =
            ComponentName(
                RECEIVER_PACKAGE_NAME, RECEIVER_MAIN_ACTIVITY_PACKAGE_NAME
            )
        startActivity(intent)
    }

    private fun sendBroadCastToMiddleMan(user: UserUiModel) {
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
        rvUsers.adapter = usersAdapter
    }

    private fun observeScreenState() {
        usersViewModel.screenState.observe(this , Observer {
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
            usersViewModel.getUsers()
        }
    }

    private fun handleLoadingVisibility(show: Boolean) {
        progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun handleSuccess(data: List<UserUiModel>) {
        usersAdapter.setList(data)
    }

    companion object{
        //bad practice
        lateinit var uiContext: Context
    }
}