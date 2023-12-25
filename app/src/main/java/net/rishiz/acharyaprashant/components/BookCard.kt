package net.rishiz.acharyaprashant.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun BookCard(
    title: String,
    subtitle: String,
    amount: Int,
    originalAmount: Int,
    coverImage: String
//    onItemclick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(10.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(7.dp),
            elevation = CardDefaults.cardElevation(10.dp)

        ) {
            AsyncImage(
                model = coverImage,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            title, style = MaterialTheme.typography.titleMedium
        )
        Text(
            subtitle, style = MaterialTheme.typography.titleSmall
        )
        if (amount == 0 || originalAmount == 0) {
            Text(
                "Available for free", style = MaterialTheme.typography.bodySmall
            )
        } else {
            Text(
                "Suggested donation: ₹$amount", style = MaterialTheme.typography.bodySmall
            )
            Text(
                "₹${originalAmount}",
                style = TextStyle(textDecoration = TextDecoration.LineThrough)
                    .merge(MaterialTheme.typography.bodySmall)
            )
        }
    }
}
