package com.remitap.quizapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Aswin on 12-09-2024.
 */
class RetrofitInstance  {
    //MacBook Air
    val baseUrl = "http://192.168.75.16:8080/quiz/"
    // in windows
//    val baseUrl = "http://10.0.0.29:8080/quiz/"

    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}