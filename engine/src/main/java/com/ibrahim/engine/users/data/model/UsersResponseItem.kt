package com.ibrahim.engine.users.data.model

import com.google.gson.Gson
import java.io.Serializable


data class UsersResponseItem(
    val address: Address,
    val company: Company,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) : Serializable {
    fun toJson(): String? {
        val gson = Gson()
       return gson.toJson(this) //convert
    }


}