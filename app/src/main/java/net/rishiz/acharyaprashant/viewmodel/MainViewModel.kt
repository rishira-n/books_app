package net.rishiz.acharyaprashant.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import net.rishiz.acharyaprashant.RetrofitInstance
import net.rishiz.acharyaprashant.model.Book
import net.rishiz.acharyaprashant.utils.BookState
import net.rishiz.acharyaprashant.utils.ViewState

/**
 * This is ViewModel class of the app
 */
class MainViewModel : ViewModel() {
    private val bookList: MutableStateFlow<List<Book>> = MutableStateFlow(emptyList())
    private val _bookState = MutableStateFlow<BookState>(BookState.Loading)
    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    private val _searchText = MutableStateFlow("")
    private val _isSearching = MutableStateFlow(false)


    val books = _viewState.asStateFlow()
    val searchText = _searchText.asStateFlow()
    val isSearching = _isSearching.asStateFlow()
    val bookState = _bookState.asStateFlow()


    //filtering books onEach search
    val searchResult = searchText.debounce(1000L).onEach { _isSearching.update { true } }
        .combine(bookList) { text, books ->
            if (text.isBlank()) {
                books
            } else {
                delay(2000L)
                books.filter {
                    doesMatchSearchQuery(book = it, query = text)
                }
            }
        }.onEach { _isSearching.update { false } }.stateIn(
            viewModelScope, SharingStarted.WhileSubscribed(5000), bookList.value
        )

    /**
     *   Api call to get book data
     */
    fun getBookData() = viewModelScope.launch(Dispatchers.IO) {
        try {

            val responce = RetrofitInstance.retrofit.getBooks().execute()
            delay(2000L)
            if (responce.isSuccessful) {
                bookList.value = responce.body()!!
                _viewState.value = ViewState.Success(bookList.value)
            }

        } catch (e: Exception) {
            _viewState.value = ViewState.Error(exception = e)
        }
    }

    /**
     * Api call to get book by id
     */
    fun getBookByID(id: String) = viewModelScope.launch(Dispatchers.IO) {
        try {

            val response = RetrofitInstance.retrofit.getBooks().execute()
            if (response.isSuccessful) {
                val book = response.body()!!.first { it.id.contentEquals(id) }
                _bookState.value = BookState.Success(book)
            }
        } catch (e: Exception) {
            _bookState.value = BookState.Error(e)
        }
    }

    /**
     * Return the boolean value if search text match
     */
    private fun doesMatchSearchQuery(book: Book, query: String): Boolean {
        return book.id.contains(query.trim(), ignoreCase = true)
    }

    /**
     * Set the text on search text change
     */
    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }

    /**
     * Clear the text of text field
     */
    fun onClearClick() {
        _searchText.value = ""
    }
}