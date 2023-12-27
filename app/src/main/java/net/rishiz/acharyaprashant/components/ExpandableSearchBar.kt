package net.rishiz.acharyaprashant.components

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.rishiz.acharyaprashant.R
import net.rishiz.acharyaprashant.navigation.MainActions
import net.rishiz.acharyaprashant.view.BookData
import net.rishiz.acharyaprashant.viewmodel.MainViewModel

@Composable
fun ExpandableSearchBar(
    actions: MainActions,
    viewModel: MainViewModel,
) {
    var isSearchExpanded by remember { mutableStateOf(false) }
    val searchText by viewModel.searchText.collectAsState()
    val searching by viewModel.isSearching.collectAsState()
    val searchResult by viewModel.searchResult.collectAsState()

    //observing search states
    if (searching) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    } else {
        Log.d("match", searchResult.toString())
        BookData(searchResult)
    }

    //Logic of expandable searchbar
    if (isSearchExpanded) {
        SearchBar(
            searchText = searchText,
            onSearchTextChange = viewModel::onSearchTextChange,
            onSearchBackClick = {
                isSearchExpanded = false
            },
            onClearClick = viewModel::onClearClick
        )
    } else {
        TopBar(
            title = stringResource(id = R.string.browse_books),
            actions = actions,
            onSearchIconClick = {
                isSearchExpanded = true
            })
    }

}