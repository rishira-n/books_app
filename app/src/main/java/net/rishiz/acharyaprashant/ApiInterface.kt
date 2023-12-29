package net.rishiz.acharyaprashant

import net.rishiz.acharyaprashant.model.BookList
import retrofit2.Call
import retrofit2.http.GET

/**
 * API interface that have getbook function
 */
interface ApiInterface {

    // suspend will paused and resumed this function later without blocking the thread
    @GET("books")
    fun getBooks(
    ): Call<BookList>
}