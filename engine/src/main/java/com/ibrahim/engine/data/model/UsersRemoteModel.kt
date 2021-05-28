package com.ibrahim.engine.data.model

import com.google.gson.Gson
import java.io.Serializable


data class UsersRemoteModel(
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