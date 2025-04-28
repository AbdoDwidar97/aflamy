package me.dwidar.aflamy.shell.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.dwidar.aflamy.shell.configs.getHeightUnit
import me.dwidar.aflamy.shell.configs.hintColor

@Composable
fun ParagraphLabel(title: String, content: String)
{
    Column {
        Text(title, style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(getHeightUnit().dp))

        Text(content, style = MaterialTheme.typography.labelMedium.copy(color = hintColor))
    }
}
