package com.ibrahim.engine.users.domain.mapper

import com.google.gson.Gson
import com.ibrahim.engine.users.data.model.UserUiModel
import com.ibrahim.engine.users.data.model.UsersRemoteModel
import com.ibrahim.engine.users.data.model.UsersResponse


fun userJsonToUserModel(userJson: String): UserUiModel{
    val gson = Gson()
    return gson.fromJson(userJson, UserUiModel::class.java)
}


 fun UserUiModel.toJson(): String {
         val gson = Gson()
         return gson.toJson(this) //convert
 }

 fun UsersRemoteModel.mapToUserUiModel(): UserUiModel {
     return UserUiModel(
         id,
         email,
         name,
         phone,
         username,
         website
     )
 }
 fun UsersResponse.mapToUserUiModelList(): List<UserUiModel> {
     return this.map {
         it.mapToUserUiModel()
     }
}