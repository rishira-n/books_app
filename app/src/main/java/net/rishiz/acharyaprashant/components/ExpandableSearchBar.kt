package net.rishiz.acharyaprashant.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import net.rishiz.acharyaprashant.view.BookData
import net.rishiz.acharyaprashant.viewmodel.MainViewModel

/**
 * Searchbar that comes on click of search icon in top bar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpandableSearchBar(
    viewModel: MainViewModel, navController: NavController, scrollBehavior: TopAppBarScrollBehavior
) {
    var isSearchExpanded by remember { mutableStateOf(false) }
    val searchText by viewModel.searchText.collectAsState()
    val searching by viewModel.isSearching.collectAsState()
    val searchResult by viewModel.searchResult.collectAsState()

    //observing search states
    if (searching) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center), color = Color(0xFFF05524)
            )

        }
    } else {
        Log.d("match", searchResult.toString())
        BookData(searchResult, navController)
    }

    //Logic of expandable searchbar
    if (isSearchExpanded) {
        SearchBar(
            searchText = searchText,
            onSearchTextChange = viewModel::onSearchTextChange,
            onSearchBackClick = {
                viewModel::onClearClick
                isSearchExpanded = false
            },
            onClearClick = viewModel::onClearClick
        )
    } else {
        TopBar(
            title = stringResource(id = R.string.browse_books),
            navController = navController,
            onSearchIconClick = {
                isSearchExpanded = true
            },
            scrollBehavior
        )
    }

}
