package com.ibrahim.engine.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
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