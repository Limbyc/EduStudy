package com.valance.petproject.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valance.petproject.R
import com.valance.petproject.data.model.LessonsData
import com.valance.petproject.ui.theme.CardColorGreen

@Composable
fun CardLesson(
    modifier: Modifier = Modifier,
    lesson: LessonsData,
) {
    Box(
        modifier = modifier
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
                    Image(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(16.dp)
                            .clip(shape = RoundedCornerShape(50)),
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
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
    CardLesson(lesson = LessonsData("title", "theme", "place", "teacher",
        "https://letsenhance.io/static/8f5e523ee6b2479e26ecc91b9c25261e/1015f/MainAfter.jpg"))
}

