//package com.example.newtineproject.ui.screens.login.server
//
//import android.telecom.Call
//import coil.intercept.Interceptor
//import com.google.android.gms.common.api.Api
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Response
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import retrofit2.http.POST
//import retrofit2.http.Query
//
//interface RetrofitInterface {
//    //sign up
//    @POST("http://ec2-43-202-57-66.ap-northeast-2.compute.amazonaws.com:8080/users/signup")
//    fun SignupApi(
//        @Query("email") email:String?,
//        @Query("password") password:String?,
//    ): retrofit2.Call<Retrofit_SignupResult?>?
//
//    @POST("http://ec2-43-202-57-66.ap-northeast-2.compute.amazonaws.com:8080/users/signin")
//    fun LoginAPi(
//        @Query("email") email:String?,
//        @Query("password") password:String?,
//    ): retrofit2.Call<Retrofit_LoginResult?>?
//
//    companion object{
//        private const val BASE_URL="http://ec2-43-202-57-66.ap-northeast-2.compute.amazonaws.com:8080"
//
//        fun create():RetrofitInterface{
//            val httpLoggingInterceptor = HttpLoggingInterceptor()
//            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//            val headerInterceptor = Interceptor {
//                val request = it.request()
//                    .newBuilder()
//                    .addHeader("Content-Type", "application/json; charset=utf-8")
//                    .build()
//                return@Interceptor it.proceed(request)
//            }
//
//            val client = okhttp3.OkHttpClient.Builder()
//                .addInterceptor(headerInterceptor)
//                .addInterceptor(httpLoggingInterceptor)
//                .addNetworkInterceptor(headerInterceptor)
//                .build()
//
//            return Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//                .create(RetrofitInterface::class.java)
//        }
//
//        }
//    }
//
//
//}