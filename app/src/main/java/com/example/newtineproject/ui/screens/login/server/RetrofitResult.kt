package com.example.newtineproject.ui.screens.login.server

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Retrofit_SignupPost(
    @SerializedName("email")
    @Expose
    var email:String,

    @SerializedName("password")
    @Expose
    var password:String,

)

data class Retrofit_LoginPost(
    @SerializedName("email")
    @Expose
    var email:String,

    @SerializedName("password")
    @Expose
    var password:String,

)

data class Retrofit_LoginResult(
    @SerializedName("userId")
    @Expose
    var userId:Int,

    @SerializedName("email")
    @Expose
    var email:String,

    @SerializedName("accessToken")
    @Expose
    var accessToken:String,

    @SerializedName("refreshToken")
    @Expose
    var refreshToken:String,

    )
