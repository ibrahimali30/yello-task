package com.ibrahim.engine.users.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson
import java.io.Serializable

@Entity
data class UserUiModel(
    @PrimaryKey
    val id: Int,
    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
) : Serializable {



}