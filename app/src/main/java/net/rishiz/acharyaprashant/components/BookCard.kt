package net.rishiz.acharyaprashant.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import net.rishiz.acharyaprashant.model.Book

/**
 * UI for Main Screen to show the books
 */
@Composable
fun BookCard(
    book: Book, onItemclick: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth(0.5f)
        .padding(10.dp)
        .clickable { onItemclick() }) {

        //Books cover image
        ElevatedCard(
            modifier = Modifier.wrapContentSize(),
            shape = RoundedCornerShape(7.dp),
            elevation = CardDefaults.cardElevation(70.dp)

        ) {
            AsyncImage(
                model = book.coverImage,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
        }

        Spacer(modifier = Modifier.height(2.dp))

        //Book title subtitle and donation
        BookTitle(book)

    }
}

@Composable
fun BookTitle(book: Book) {
    Text(
        book.title, style = MaterialTheme.typography.titleMedium
    )
    Text(
        book.subtitle, style = MaterialTheme.typography.titleSmall
    )
    if (book.amount == 0 || book.originalAmount == 0) {
        Text(
            "Available for free", style = MaterialTheme.typography.bodySmall
        )
    } else {
        Text(
            "Suggested donation: ₹${book.amount}", style = MaterialTheme.typography.bodySmall
        )
        Text(
            "₹${book.originalAmount}",
            style = TextStyle(textDecoration = TextDecoration.LineThrough).merge(MaterialTheme.typography.bodySmall)
        )
    }
}
