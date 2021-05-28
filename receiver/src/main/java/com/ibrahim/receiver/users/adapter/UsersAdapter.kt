package com.ibrahim.receiver.users.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.engine.users.data.model.UserUiModel
import com.ibrahim.receiver.R
import kotlinx.android.synthetic.main.user_list_item.view.*


class UsersAdapter(
    val data: ArrayList<UserUiModel> = java.util.ArrayList()
) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])

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

        fun bind(model: UserUiModel) {
            view.tv_name.text = model.name
            view.tv_email.text = model.email
            view.tv_phone.text = model.phone

        }
    }
}