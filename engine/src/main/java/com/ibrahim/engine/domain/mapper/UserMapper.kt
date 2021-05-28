package com.ibrahim.engine.domain.mapper

import com.google.gson.Gson
import com.ibrahim.engine.data.model.UserUiModel
import com.ibrahim.engine.data.model.UsersRemoteModel
import com.ibrahim.engine.data.model.UsersResponse


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