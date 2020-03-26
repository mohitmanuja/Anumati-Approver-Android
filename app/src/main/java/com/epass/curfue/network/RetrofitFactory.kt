package com.epass.curfue.network

import android.content.Context
//import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitFactory {
    const val BASE_URL = "http://13.233.33.10:8091"
    var client: OkHttpClient? = null



    fun makeRetrofitService(context: Context): RetrofitService {
        if (client == null){
            client = NetworkUtil.getUnsafeOkHttpClient().connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
//                .addInterceptor(ChuckInterceptor(context))
                .build()
        }

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build().create(RetrofitService::class.java)
    }
}