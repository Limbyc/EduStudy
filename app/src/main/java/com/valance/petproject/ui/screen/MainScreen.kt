package com.valance.petproject.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valance.petproject.R
import com.valance.petproject.data.model.ItemData
import com.valance.petproject.data.model.LessonsData
import com.valance.petproject.ui.components.CardLesson
import com.valance.petproject.ui.components.CardSubject
import com.valance.petproject.ui.components.MainText
import com.valance.petproject.ui.components.SecondaryText
import com.valance.petproject.ui.theme.CardColorBlew
import com.valance.petproject.ui.theme.CardColorYellow
import com.valance.petproject.ui.theme.Green
import com.valance.petproject.ui.theme.LightGreen

@Composable
fun MainScreen() {
    SearchBarAndCloudPartOfScreen()
    BottomPart(items = listOf(), lessons = listOf())
    PicturePart()
}

@Composable
fun SearchBarAndCloudPartOfScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Green)
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 44.dp, start = 28.dp)
    ) {
        Row() {
            Box(
                modifier = modifier
                    .width(48.dp)
                    .height(48.dp)
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(color = LightGreen)
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.searchicon),
                    contentDescription = null,
                    modifier = modifier
                        .align(Alignment.Center)
                )

            }
            Box(
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.cloud), contentDescription = null,
                    modifier = modifier
                        .align(Alignment.TopEnd)
                )
            }
        }
    }
}

@Composable
fun PicturePart(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.globus),
            contentDescription = null,
            modifier = modifier
                .align(Alignment.TopEnd)
                .offset(y = 82.dp)
        )
    }
}

@Composable
fun BottomPart(
    modifier: Modifier = Modifier,
    items: List<ItemData>,
    lessons: List<LessonsData>,
) {
    Box(
        modifier = modifier
            .padding(top = 340.dp)
            .clip(shape = RoundedCornerShape(topStart = 28.dp))
            .background(color = Color.White)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 28.dp, start = 28.dp)
        ) {
            MainText(text = R.string.subject.toString())

            SecondaryText(
                modifier = modifier.padding(top = 2.dp),
                text = R.string.recommendations_for_you.toString(),
            )
            SubjectList(items = items)

            MainText(
                modifier = modifier.padding(top = 28.dp),
                text = R.string.your_schedule.toString(),
            )
            SecondaryText(
                modifier = modifier.padding(top = 5.dp),
                text = R.string.next_lessons.toString(),
            )
            LessonList(lessons = lessons)
        }
    }
}

@Composable
fun LessonList(
    modifier: Modifier = Modifier,
    lessons: List<LessonsData>,
) {
    LazyColumn(
        modifier = modifier.padding(end = 28.dp, top = 16.dp)
    ) {
        items(lessons) { lesson ->
            CardLesson(lesson = lesson)
        }
    }
}

@Composable
fun SubjectList(
    modifier: Modifier = Modifier,
    items: List<ItemData>,
) {
    LazyRow(
        modifier = modifier.padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(items) { item ->
            CardSubject(item = item)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}

