package com.example.newtineproject.ui.screens.login.server

import android.telecom.Call
import coil.intercept.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitInterface {
    //sign up
    @POST("http://ec2-52-78-221-52.ap-northeast-2.compute.amazonaws.com:8080/user/signup/")
    fun SignupPost(
        @Body postData:Retrofit_SignupPost,

    ): retrofit2.Call<Void>

    @POST("http://ec2-52-78-221-52.ap-northeast-2.compute.amazonaws.com:8080/user/signin/")
    fun LoginPost(
        @Body postData:Retrofit_LoginPost,

    ): retrofit2.Call<Retrofit_LoginResult?>?


}