package net.rishiz.acharyaprashant.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import net.rishiz.acharyaprashant.model.Book

/**
 * UI of Book Screen to show the book details
 */
@Composable
fun BookDetailsCard(book: Book) {

    //Transparent white bg
    Box(
        Modifier
            .fillMaxSize()
            .wrapContentHeight()
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.Transparent)
            .padding(start = 20.dp, end = 16.dp, top = 40.dp),
        contentAlignment = Alignment.Center
    ) {

        //ElevatedCard box layout
        ElevatedCard(
            modifier = Modifier.size(width = 300.dp, height = 350.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        ) {}

        //content
        BookImageContentView(book)
    }

}

@Composable
private fun BookImageContentView(book: Book) {
    // content
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        // Book cover image
        Card(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(7.dp),
            elevation = CardDefaults.cardElevation(40.dp)

        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = book.coverImage
                ),
                contentDescription = null,
                modifier = Modifier.size(180.dp, 250.dp),
                contentScale = ContentScale.Crop
            )
        }
        //Transparent layout to set the height of above ElevatedCard box layout
        Column(
            Modifier
                .height(18.dp)
                .background(Color.Transparent)
        ) {}

        //Layout for Book title subtitle and donation
        Column(
            modifier = Modifier.wrapContentSize(),
            // .padding(bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            //donation
            DonationDetails(book = book)
            Spacer(modifier = Modifier.height(5.dp))
            BookButtons()
        }
    }
}

@Composable
fun DonationDetails(book: Book) {

    Text(
        book.title, style = MaterialTheme.typography.titleLarge
    )
    Text(
        book.subtitle, style = MaterialTheme.typography.titleMedium
    )
    if (book.amount == 0 || book.originalAmount == 0) {
        Text(
            "Available for free", style = MaterialTheme.typography.titleSmall
        )
    } else {
        Text(
            "Suggested donation: ₹${book.amount}", style = MaterialTheme.typography.titleSmall
        )
        Text(
            "₹${book.originalAmount}",
            style = TextStyle(textDecoration = TextDecoration.LineThrough).merge(MaterialTheme.typography.bodySmall)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookButtons() {
    var showDialog by remember { mutableStateOf(false) }

    Row(
        Modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ElevatedButton(onClick = { showDialog = true }) {
            Row {
                Icon(Icons.Default.Favorite, contentDescription = "favorite")
            }
        }
        ElevatedButton(onClick = { showDialog = true }) {
            Row {
                Icon(Icons.Filled.ShoppingCart, contentDescription = "Shopping cart")
            }

        }
        ElevatedButton(onClick = { showDialog = true }) {
            Row {
                Icon(Icons.Filled.ThumbUp, contentDescription = "Shopping cart")
            }
        }
    }
    if (showDialog) {
        AlertDialog(onDismissRequest = {
            showDialog = false
        },
            title = { Text("Greeting ^_^") },
            text = { Text("Thank you for visit!") },
            confirmButton = {
                Button(onClick = {
                    showDialog = false
                }) {
                    Text("OK")
                }
            })
    }
}

