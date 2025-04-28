package me.dwidar.aflamy.shell.presentation.common

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import me.dwidar.aflamy.shell.configs.cardRoundedCorner
import me.dwidar.aflamy.shell.configs.getWidthUnit

@Composable
fun PrimaryButton(title: String, icon: ImageVector?, enabled: Boolean = true, onClick: () -> Unit) {
    Button(
        enabled = enabled,
        shape = RoundedCornerShape(cardRoundedCorner),
        onClick = { onClick() }) {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = "Favorite"
            )
        }

        Spacer(modifier = Modifier.width((getWidthUnit() * 4).dp))

        Text(text = title, style = MaterialTheme.typography.labelMedium)
    }
}
