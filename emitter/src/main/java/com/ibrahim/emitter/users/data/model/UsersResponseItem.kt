package com.ibrahim.emitter.users.data.model

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
) : Serializable