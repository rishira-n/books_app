package net.rishiz.acharyaprashant.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@ExperimentalComposeUiApi
@Composable
fun OutlineTextInputField(
    label: String, value: String, onValueChanged: (String) -> Unit,
    onClearClicked: () -> Unit,
    onSearchBackClick: () -> Unit,){
    val keyboardController = LocalSoftwareKeyboardController.current
    var showClearBtn by remember {
       mutableStateOf(false)
    }
    val focusRequester= remember {
        FocusRequester()
    }
    LaunchedEffect(Unit ){
         focusRequester.requestFocus()
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp)
            .shadow(5.dp, CircleShape)
            .onFocusChanged {
                showClearBtn = (it.isFocused)
            }.focusRequester(focusRequester),
        shape = CircleShape,
        value = value,
        onValueChange = {
            onValueChanged(it)
        },
//        label = { LabelView(title = label) },
        textStyle = MaterialTheme.typography.bodyLarge,
        colors = outLinedTextFieldColors(),
        keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Search),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            }
        ),
        leadingIcon = {
            IconButton(onClick = { onSearchBackClick() }) {
                Icon(
                   imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
//                    tint = Cranberry,
                    modifier = Modifier.size(22.dp)
                )
                
            }
        },
        trailingIcon = {
            AnimatedVisibility(visible = showClearBtn,
                enter= fadeIn(),
                exit = fadeOut()
            ) {
            IconButton(onClick = { onClearClicked() }) {
                if(value.isNotBlank()) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null,
//                        tint = Cranberry,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
            }
        },
        maxLines = 1,
        singleLine = true,

    )

}

@Composable
fun LabelView(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelSmall,
        textAlign = TextAlign.Start,
        color = colorScheme.primary
    )
}

@Composable
fun outLinedTextFieldColors()= OutlinedTextFieldDefaults.colors(
    focusedContainerColor = colorScheme.surface,
    unfocusedContainerColor = colorScheme.surface,
    disabledContainerColor = colorScheme.surface,
    cursorColor = colorScheme.onBackground,
    focusedBorderColor = Color.Transparent,
    unfocusedBorderColor = Color.Transparent,
)

