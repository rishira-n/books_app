package net.rishiz.acharyaprashant

import net.rishiz.acharyaprashant.model.BookList
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("books")
    fun getBooks(
    ): Call<BookList>

}