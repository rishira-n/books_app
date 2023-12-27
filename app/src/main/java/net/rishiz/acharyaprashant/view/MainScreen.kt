package net.rishiz.acharyaprashant.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import net.rishiz.acharyaprashant.components.BookCard
import net.rishiz.acharyaprashant.components.ExpandableSearchBar
import net.rishiz.acharyaprashant.model.Book
import net.rishiz.acharyaprashant.navigation.MainActions
import net.rishiz.acharyaprashant.utils.ViewState
import net.rishiz.acharyaprashant.viewmodel.MainViewModel


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("StateFlowValueCalledInComposition", "SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MainScreen(actions: MainActions, viewModel: MainViewModel) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                ExpandableSearchBar(actions = actions, viewModel = viewModel)
            }) {
            Column(Modifier.padding(it)) {

                when (val result = viewModel.books.value) {
                    is ViewState.Success -> {
                        BookData(result.data)
                    }

                    ViewState.Loading ->{
                        Box(modifier = Modifier.fillMaxSize()){
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                    ViewState.Empty -> Text("No result found!")
                    is ViewState.Error -> Text("Error occur: ${result.exception}")
                }

            }

        }
    }
}

@Composable
fun BookData(data: List<Book>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(data) { book ->
            BookCard(
                title = book.title,
                subtitle = book.subtitle,
                amount = book.amount,
                originalAmount = book.originalAmount,
                coverImage = book.coverImage
            )
        }
    }
}

