package com.valance.petproject.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.valance.petproject.R
import com.valance.petproject.domain.model.SubjectCard

fun parseColor(colorString: String): Color {
    return Color(android.graphics.Color.parseColor(colorString))
}

@Composable
fun CardSubject(
    modifier: Modifier = Modifier,
    item: SubjectCard,
    ) {

    val color = parseColor(item.color)

    Box(
        modifier = modifier
            .padding(bottom = 10.dp)
            .width(149.dp)
            .height(119.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = color)
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            Row(
                modifier = modifier.fillMaxWidth()
            ) {

                AsyncImage(
                    modifier = modifier
                        .padding(
                            top = 16.dp,
                            start = 16.dp
                        )
                        .size(24.dp)
                        .clip(CircleShape),
                    model = item.image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = modifier.weight(1f))

                Image(
                    modifier = modifier
                        .padding(
                        top = 16.dp,
                        end = 16.dp
                    ),
                    painter = painterResource(id = R.drawable.ellipsis),
                    contentDescription = null,
                )
            }
            Spacer(modifier = modifier.weight(1f))

            Text(
                modifier = modifier
                    .padding(
                    start = 16.dp,
                    bottom = 16.dp
                ),
                text = item.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
            )
        }
    }
}

@Preview
@Composable
private fun CardSubjectPreview() {
    CardSubject(item = SubjectCard("Hello", Color.Gray.toString(),  "R.drawable.earth" ))
}


