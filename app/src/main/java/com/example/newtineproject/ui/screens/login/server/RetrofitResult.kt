package com.example.newtineproject.ui.screens.login.server

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Retrofit_SignupResult(
    @SerializedName("email")
    @Expose
    var email:String,

    @SerializedName("password")
    @Expose
    var password:String,

)

data class Retrofit_LoginResult(
    @SerializedName("email")
    @Expose
    var email:String,

    @SerializedName("password")
    @Expose
    var password:String,

    @SerializedName("kakao_email")
    @Expose
    var kakao_email:String,

    )
