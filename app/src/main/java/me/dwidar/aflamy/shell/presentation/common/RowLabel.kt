package me.dwidar.aflamy.shell.presentation.common

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import me.dwidar.aflamy.shell.configs.hintColor

@Composable
fun RowLabel(title: String, value: String)
{
    Row {
        Text(title, style = MaterialTheme.typography.titleSmall.copy(color = hintColor))
        Text(value, style = MaterialTheme.typography.titleSmall)
    }
}
