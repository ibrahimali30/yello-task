package com.ibrahim.engine.users.domain.mapper

import com.google.gson.Gson
import com.ibrahim.engine.users.data.model.UsersResponseItem


fun userJsonToUserModel(userJson: String): UsersResponseItem{
    val gson = Gson()
    return gson.fromJson(userJson, UsersResponseItem::class.java)
}