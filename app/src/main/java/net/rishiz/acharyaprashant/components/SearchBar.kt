package net.rishiz.acharyaprashant.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.res.stringResource
import net.rishiz.acharyaprashant.R

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun SearchBar( searchText: String,
                 onSearchTextChange: (String) -> Unit,
                 onSearchBackClick: () -> Unit,
                onClearClick:() -> Unit,
    ) {
    TopAppBar(
    title = {
        OutlineTextInputField(label = stringResource(id = R.string.text_search),
            value = searchText ,
            onValueChanged =
            {
                onSearchTextChange(it)
            },
            onClearClicked = {  onClearClick()},
            onSearchBackClick=onSearchBackClick)
    })

}