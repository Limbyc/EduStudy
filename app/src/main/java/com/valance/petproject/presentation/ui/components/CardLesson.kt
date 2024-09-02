package com.valance.petproject.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.valance.petproject.R
import com.valance.petproject.domain.model.LessonCard
import com.valance.petproject.presentation.ui.theme.CardColorGreen

@Composable
fun CardLesson(
    modifier: Modifier = Modifier,
    lesson: LessonCard,
) {
    Box(
        modifier = modifier
            .padding(bottom = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = CardColorGreen)
            .padding(16.dp)
    ) {
        Column {
            Row {
                Text(
                    text = lesson.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                )

                Spacer(modifier = Modifier.weight(1f))

                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ellipsis),
                    tint = Color.Unspecified,
                    contentDescription = null,
                )
            }

            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = lesson.theme,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
            )

            Column(modifier = Modifier.padding(top = 15.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.location),
                        contentDescription = null,
                    )
                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = lesson.place,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.White,
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {

                    AsyncImage(
                        modifier = modifier
                            .padding(4.dp)
                            .size(16.dp)
                            .clip(CircleShape),
                        model = lesson.teacherIcon,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        modifier = Modifier.padding(start = 5.dp),
                        text = lesson.teacher,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.Black,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CardLessonPreview() {
    CardLesson(lesson = LessonCard("title", "theme", "place", "teacher",
        "https://www.simplilearn.com/ice9/free_resources_article_thumb/what_is_image_Processing.jpg", "","",""))
}

