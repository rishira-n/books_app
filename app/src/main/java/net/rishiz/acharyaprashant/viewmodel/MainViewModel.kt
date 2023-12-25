package net.rishiz.acharyaprashant.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import net.rishiz.acharyaprashant.RetrofitInstance
import net.rishiz.acharyaprashant.model.Book
import net.rishiz.acharyaprashant.utils.ViewState

class MainViewModel: ViewModel() {
    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val books = _viewState.asStateFlow()
//    val books= MutableLiveData<List<Book>>()

    fun getBookData()=viewModelScope.launch(Dispatchers.IO){
        val bookList= mutableListOf<Book>()
        try {
            val responce = RetrofitInstance.retrofit.getBooks().execute()
            if (responce.isSuccessful) {
                responce.body()?.forEach{
                    bookList.add(it)
//                    _viewState.value=ViewState.Success(it)
                }
                _viewState.value = ViewState.Success(bookList)


            }
        }catch (e:Exception){
          _viewState.value=ViewState.Error(exception =e)
        }
    }

}