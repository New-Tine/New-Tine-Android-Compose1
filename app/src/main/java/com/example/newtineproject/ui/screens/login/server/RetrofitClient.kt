package com.example.newtineproject.ui.screens.login.server

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    private var instance:RetrofitClient?=null
    private var retrofitInterface:RetrofitInterface
    private var baseUrl:String = "http://ec2-43-202-57-66.ap-northeast-2.compute.amazonaws.com:8080/"

    // Gson 설정: lenient 모드를 활성화
//    val gson: Gson = GsonBuilder()
//        .setLenient()
//        .create()

    init {
        val retrofit=Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofitInterface=retrofit.create(RetrofitInterface::class.java)
    }

    fun getInstance():RetrofitClient?{
        if(instance==null){
            instance=RetrofitClient()
        }
        return instance
    }

    fun getRetrofitInterface():RetrofitInterface{
        return retrofitInterface
    }
}