package com.my.profiledetails

import android.util.Log
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val BASE_URL="https://dummyapi.io/data/v1/"

interface ProfileInterface {
    @Headers("app-id:621f289207eff61c6d85d8ca")
    @GET("user/60d0fe4f5311236168a109d0/")
   // fun getHeadlines( @Query("page")page:Int): Call<List<Details>>
    fun getUserData():Call<Details>
}

object ProfileService{
    val profileInstance: ProfileInterface
    init {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        profileInstance=retrofit.create(ProfileInterface::class.java)
    }
}