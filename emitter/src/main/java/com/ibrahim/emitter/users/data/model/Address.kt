package com.ibrahim.emitter.users.data.model

import java.io.Serializable

data class Address(
    val city: String,
    val geo: Geo,
    val street: String,
    val suite: String,
    val zipcode: String
) : Serializable