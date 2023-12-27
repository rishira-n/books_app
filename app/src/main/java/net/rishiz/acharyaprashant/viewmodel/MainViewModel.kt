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
import net.rishiz.acharyaprashant.utils.ViewState

class MainViewModel: ViewModel() {
   private val bookList:MutableStateFlow<List<Book>> =MutableStateFlow(emptyList())
   private val _filteredBooks:MutableStateFlow<List<Book>> =MutableStateFlow(emptyList())

    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val books = _viewState.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()
    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

   //filtering books onEach search
    val searchResult = searchText
        .debounce(1000L)
        .onEach { _isSearching.update { true } }
        .combine(bookList) { text, books ->
            if(text.isBlank()) {
                books
            } else {
                delay(2000L)
                books.filter {
                    doesMatchSearchQuery(book=it,query=text)
                }
            }
        }
        .onEach { _isSearching.update { false } }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            bookList.value
        )

    fun getBookData()=viewModelScope.launch(Dispatchers.IO){
        try {
            val responce = RetrofitInstance.retrofit.getBooks().execute()
            if (responce.isSuccessful) {
                bookList.value= responce.body()!!
                _viewState.value = ViewState.Success(bookList.value)

            }
        }catch (e:Exception){
          _viewState.value=ViewState.Error(exception =e)
        }
    }

    private fun doesMatchSearchQuery(book: Book, query:String): Boolean {
        return book.id.contains(query.trim(), ignoreCase =true )
    }
    fun onSearchTextChange(text:String){
        _searchText.value=text
    }
fun onClearClick() {
    _searchText.value = ""

}








}