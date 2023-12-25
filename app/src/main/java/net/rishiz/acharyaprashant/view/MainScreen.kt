package net.rishiz.acharyaprashant.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import net.rishiz.acharyaprashant.R
import net.rishiz.acharyaprashant.components.BookCard
import net.rishiz.acharyaprashant.model.Book
import net.rishiz.acharyaprashant.navigation.MainActions
import net.rishiz.acharyaprashant.utils.ViewState
import net.rishiz.acharyaprashant.viewmodel.MainViewModel


@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MainScreen(actions: MainActions, viewModel: MainViewModel) {

    Surface(
        modifier=Modifier.fillMaxSize(),
        color=MaterialTheme.colorScheme.background
    ) {
        Column {
            Spacer(modifier = Modifier.height(10.dp))
            SearchBar(modifier = Modifier
               .fillMaxWidth()
               .padding(16.dp),
               hint = "Search Books"){
                //todo
            }
            Spacer(modifier = Modifier.height(10.dp))
            when(val result=viewModel.books.value) {
                is ViewState.Success->{
                    BookData(result.data)
                }
                ViewState.Loading -> Text("Loading")
                ViewState.Empty -> Text("No result found!")
                is ViewState.Error -> Text("Error occur: ${result.exception}")
            }
        }
    }
}
@Composable
fun BookData(books: List<Book>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        items(books){book->
            BookCard(
                title = book.title,
                subtitle = book.subtitle,
                amount = book.amount,
                originalAmount = book.originalAmount,
                coverImage =book.coverImage
            )
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier=Modifier
        .fillMaxWidth(0.5f),
    hint:String="",
    onSearch:(String)->Unit={}){
    var searchQuery  by remember {
        mutableStateOf("")
    }
    var isHintDisplay by remember {
        mutableStateOf(hint!="")
    }
    Row(modifier= Modifier.padding(8.dp)){
        Box(modifier = Modifier
            .fillMaxWidth(0.8f)
            .background(MaterialTheme.colorScheme.primaryContainer, CircleShape)
            ) {
            TextField(value =searchQuery ,
                onValueChange = {newValue ->
                    searchQuery=newValue
                },
                maxLines = 1,
                singleLine = true,
                textStyle = TextStyle(Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(5.dp, CircleShape),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                placeholder = {Text("Search Books")}
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        FloatingActionButton(onClick = { /*TODO*/ },
            shape= CircleShape){
            Icon(painter= painterResource(R.drawable.baseline_mic_24),"search")
        }

        }
    }

