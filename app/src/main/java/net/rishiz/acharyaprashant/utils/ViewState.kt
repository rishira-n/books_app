package net.rishiz.acharyaprashant.utils

import net.rishiz.acharyaprashant.model.Book


sealed class ViewState {
    data class Success(val data: List<Book>):ViewState()
    data class Error(val exception: Throwable):ViewState()
    data object Empty:ViewState()
    data object Loading:ViewState()
}