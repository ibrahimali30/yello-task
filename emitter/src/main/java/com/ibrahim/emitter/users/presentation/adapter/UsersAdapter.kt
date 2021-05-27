package com.ibrahim.emitter.users.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ibrahim.emitter.R
import com.ibrahim.emitter.users.data.model.UsersResponseItem
import kotlinx.android.synthetic.main.layout_words_item.view.*

class UsersAdapter(
    val data: ArrayList<UsersResponseItem> = java.util.ArrayList(),
    val onItemClicked: (UsersResponseItem)-> Unit
) :
    RecyclerView.Adapter<UsersAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_words_item, parent, false)
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

    fun setList(list: List<UsersResponseItem>) {
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
        fun bind(model: UsersResponseItem) {
            view.tvWordTitle.text = model.name
            view.tvWordCount.text = model.username

        }
    }
}