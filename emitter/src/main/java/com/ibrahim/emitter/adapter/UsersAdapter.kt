package com.ibrahim.emitter.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.emitter.R
import com.ibrahim.engine.data.model.UserUiModel
import kotlinx.android.synthetic.main.user_list_item.view.*

class UsersAdapter(
    val data: ArrayList<UserUiModel> = java.util.ArrayList(),
    val onItemClicked: (UserUiModel)-> Unit
) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
        holder.view.setOnClickListener {
            onItemClicked(data[position])
        }
    }

    fun setList(list: List<UserUiModel>) {
        data.clear()
        data.addAll(list)
        notifyDataSetChanged()
    }

    fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    class ViewHolder constructor(var view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("CheckResult")
        fun bind(model: UserUiModel) {
            view.tv_name.text = model.name
            view.tv_email.text = model.email
            view.tv_phone.text = model.phone

        }
    }
}