package net.rishiz.acharyaprashant.utils

import net.rishiz.acharyaprashant.model.Book

sealed class SearchState {
    val searchbookList: List<Book> = arrayListOf()
    data object SearchQuery:SearchState()
    data object Searching:SearchState()
    data object Loading:SearchState()
}