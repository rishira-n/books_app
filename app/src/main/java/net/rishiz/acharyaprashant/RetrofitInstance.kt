package net.rishiz.acharyaprashant

import net.rishiz.acharyaprashant.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitInstance {
    //Logging HTTP request and response
    private val loggig=HttpLoggingInterceptor().apply{
        level=HttpLoggingInterceptor.Level.BODY
    }
   private val client=OkHttpClient.Builder()
       .addInterceptor(loggig)
       .build()

    val retrofit: ApiInterface = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build().create()
}