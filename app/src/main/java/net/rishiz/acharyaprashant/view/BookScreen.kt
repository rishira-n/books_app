package net.rishiz.acharyaprashant.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import net.rishiz.acharyaprashant.R
import net.rishiz.acharyaprashant.components.BookDetailsCard
import net.rishiz.acharyaprashant.utils.BookState
import net.rishiz.acharyaprashant.viewmodel.MainViewModel

/**
 * This is UI of Book Screen that display the book details
 */
@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookScreen(viewModel: MainViewModel, navController: NavController) {
    var title by remember {
        mutableStateOf("")
    }
    //Align the component in structured way
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = title) }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.text_back_button)
                )
            }
        })
    }) {

        //Observing book states
        when (val result = viewModel.bookState.value) {

            is BookState.Error -> {
                Text(text = "Error found: ${result.exception}")
            }

            BookState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center), color = Color(0xFFF05524)
                    )
                }
            }

            is BookState.Success -> {
                val book = result.data
                title = book.title
                LazyColumn(modifier = Modifier.padding(it)) {
                    // Book Details Card
                    item {
                        BookDetailsCard(book)
                    }
                }
            }
        }
    }

}
