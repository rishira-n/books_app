package net.rishiz.acharyaprashant.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import net.rishiz.acharyaprashant.R

/**
 * TopBar
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    title: String,
    navController: NavController,
    onSearchIconClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {

    TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                containerColor = MaterialTheme.colorScheme.onPrimaryContainer,
//                titleContentColor = MaterialTheme.colorScheme.primary
//            ),
        title = {
            Text(
                title, maxLines = 1, overflow = TextOverflow.Ellipsis
            )
        }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.text_back_button),
                    modifier = Modifier.clickable(onClick = {
                        navController.navigateUp()
                    })
                )
            }
        }, actions = {
            IconButton(onClick = { onSearchIconClick() }) {
                Icon(
                    imageVector = Icons.Default.Search, contentDescription = stringResource(
                        id = R.string.text_search
                    )
                )
            }
        }, scrollBehavior = scrollBehavior
    )

}



