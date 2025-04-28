package me.dwidar.aflamy.shell.presentation.movie_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import me.dwidar.aflamy.core.model.casts.CastMemberModel
import me.dwidar.aflamy.shell.configs.cardBackgroundColor
import me.dwidar.aflamy.shell.configs.cardRoundedCorner
import me.dwidar.aflamy.shell.configs.getHeightUnit
import me.dwidar.aflamy.shell.configs.getWidthUnit
import me.dwidar.aflamy.shell.configs.screenHeight
import me.dwidar.aflamy.shell.configs.screenWidth

@Composable
fun CastCard(castMember: CastMemberModel)
{
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(cardRoundedCorner))
            .width((screenWidth() * 0.45).dp)
            .background(cardBackgroundColor)
    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = getHeightUnit().dp, horizontal = getWidthUnit().dp)){
            AsyncImage(
                model = castMember.profilePath,
                contentDescription = "Example image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size((screenHeight() * screenWidth() * 0.00017).dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.width((getWidthUnit() * 4).dp))

            Column (
                modifier = Modifier
                    .padding(horizontal = (getWidthUnit() * 2).dp),
                verticalArrangement = Arrangement.Center){
                Text(
                    text = castMember.name,
                    style = MaterialTheme.typography.labelLarge,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(getHeightUnit().dp))

                Text(
                    text = castMember.knownForDepartment,
                    style = MaterialTheme.typography.labelSmall,
                )
            }

        }
    }
}
