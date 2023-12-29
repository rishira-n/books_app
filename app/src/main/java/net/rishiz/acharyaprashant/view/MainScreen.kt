package net.rishiz.acharyaprashant.view

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay
import net.rishiz.acharyaprashant.components.BookCard
import net.rishiz.acharyaprashant.components.ExpandableSearchBar
import net.rishiz.acharyaprashant.components.ShimmerVerticalGridView
import net.rishiz.acharyaprashant.model.Book
import net.rishiz.acharyaprashant.navigation.Screen
import net.rishiz.acharyaprashant.utils.ViewState
import net.rishiz.acharyaprashant.viewmodel.MainViewModel

/**
 * This is UI of Main Screen that display all books
 */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("StateFlowValueCalledInComposition", "SuspiciousIndentation")
@RequiresApi(Build.VERSION_CODES.R)
@Composable
fun MainScreen(navController: NavController, viewModel: MainViewModel) {

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    var isRefreshing by remember{
        mutableStateOf(false)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LaunchedEffect(isRefreshing){
            delay(2000L)
            isRefreshing=false
        }

        //Align the component in structured way
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                ExpandableSearchBar(
                    navController = navController, viewModel = viewModel,
                    scrollBehavior = scrollBehavior
                )
            },
        ) {

            Column(Modifier.padding(it)) {

                //Refreshing
                SwipeRefresh(state = rememberSwipeRefreshState(isRefreshing = isRefreshing), onRefresh = {
                viewModel.getBookData()
                    isRefreshing=true}
                ) {
                    //Observing book data state
                    when (val result = viewModel.books.value) {
                        is ViewState.Success -> {
                        //    isRefreshing=false
                            BookData(result.data, navController)
                        }

                        ViewState.Loading -> {
                            LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                                items(10) {
                                    ShimmerVerticalGridView(modifier = Modifier
                                        .padding(2.dp)
                                    )
                                }
                            }
                        }

                        ViewState.Empty -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ){ Text("No result found!")}
                        }
                        is ViewState.Error ->{
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                            ){ Text("Error occur: ${result.exception}")}
                        }
                    }
                }
            }
        }
    }
}

/**
 * Vertical grid layout to show all the books
 */
@Composable
fun BookData(data: List<Book>, navController: NavController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2)
    ) {
        items(data) { book ->
            BookCard(book
            ) { navController.navigate("${Screen.BookScreen.route}/${book.id}")
            }

        }
    }
}