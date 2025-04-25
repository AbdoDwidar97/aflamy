package me.dwidar.aflamy.shell.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import me.dwidar.aflamy.shell.configs.getDefaultIconSize
import me.dwidar.aflamy.shell.configs.hintColor
import me.dwidar.aflamy.shell.configs.textFieldColor
import me.dwidar.aflamy.shell.configs.textFieldRoundedCorner
import me.dwidar.aflamy.shell.configs.white

@Composable
fun AflamySearchBar(
    query: String,
    onQueryChanged: (String) -> Unit,
) {
    TextField(
        value = query,
        onValueChange = onQueryChanged,
        placeholder = { Text("Search movies...", style = MaterialTheme.typography.bodyMedium.copy(color = hintColor)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = hintColor,
                modifier = Modifier.size(getDefaultIconSize())
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(textFieldRoundedCorner),
        colors = TextFieldDefaults.colors(
            cursorColor = white,
            focusedTextColor = white,
            focusedContainerColor = textFieldColor,
            unfocusedContainerColor = textFieldColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier.fillMaxWidth()
    )
}