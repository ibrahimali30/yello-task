package com.ibrahim.receiver.users.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.receiver.users.adapter.UsersAdapter
import com.ibrahim.engine.viewmodel.UsersLocalViewModel
import com.ibrahim.receiver.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_saved_users.*
import javax.inject.Inject


@AndroidEntryPoint
class UsersListActivity : AppCompatActivity() {

    @Inject
    lateinit var wordsViewModel: UsersLocalViewModel

    val usersAdapter: UsersAdapter by lazy { UsersAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved_users)

        wordsViewModel.getUsersFromLocalDB()

        observeScreenState()
        initRecyclerView()

    }

    private fun initRecyclerView() {
        rvSavedUsers.apply {
            layoutManager = LinearLayoutManager(this@UsersListActivity)
            adapter = this@UsersListActivity.usersAdapter
        }

    }

    private fun observeScreenState() {
        wordsViewModel.savedUsersLiveData.observe(this , Observer {
            usersAdapter.setList(it)
        })
    }


}