package net.rishiz.acharyaprashant.utils

import net.rishiz.acharyaprashant.model.Book

/**
 * This class keep track the state of book
 */
sealed class BookState {
    data object Loading : BookState()
    data class Success(val data: Book) : BookState()
    data class Error(val exception: Throwable) : BookState()
}