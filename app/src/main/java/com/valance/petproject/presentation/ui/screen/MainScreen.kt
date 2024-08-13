package com.valance.petproject.presentation.ui.screen

import android.util.Log
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valance.petproject.R
import com.valance.petproject.domain.model.LessonCard
import com.valance.petproject.domain.model.SubjectCard
import com.valance.petproject.presentation.state.LessonCardViewState
import com.valance.petproject.presentation.state.SubjectCardViewState
import com.valance.petproject.presentation.ui.components.CardLesson
import com.valance.petproject.presentation.ui.components.CardSubject
import com.valance.petproject.presentation.ui.components.GeneralTextContent
import com.valance.petproject.presentation.ui.theme.Green
import com.valance.petproject.presentation.ui.theme.LightGreen
import com.valance.petproject.presentation.viewmodel.LessonCardViewModel
import com.valance.petproject.presentation.viewmodel.SubjectCardViewModel
import org.koin.androidx.compose.getViewModel


@Composable
fun MainScreen() {
    val lessonCardViewModel: LessonCardViewModel = getViewModel()
    val state by lessonCardViewModel.state.collectAsState()

    val subjectCardViewModel: SubjectCardViewModel = getViewModel()
    val subState by subjectCardViewModel.state.collectAsState()

    SearchBarAndCloudPartOfScreen()
    BottomPart(lessonState = state, subjectState = subState )
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
    lessonState: LessonCardViewState,
    subjectState: SubjectCardViewState,
) {
    when (lessonState) {
        is LessonCardViewState.Loading -> {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
            ) {
                CircularProgressIndicator()
            }
        }

        is LessonCardViewState.Success -> {
            val lessons = lessonState.lessons

            Box(
                modifier = modifier
                    .padding(top = 340.dp)
                    .clip(shape = RoundedCornerShape(topStart = 28.dp))
                    .background(color = Color.White)
                    .fillMaxSize()
                    .padding(start = 28.dp, top = 28.dp)
            ) {
                Column {
                    GeneralTextContent(
                        mainText = stringResource(R.string.subject),
                        secondaryText = stringResource(R.string.recommendations_for_you)
                    )
                    when (subjectState) {
                        is SubjectCardViewState.Success -> {
                            SubjectList(items = subjectState.subject)
                        }
                        is SubjectCardViewState.Loading -> {

                        }
                        is SubjectCardViewState.Error -> {

                        }
                    }
                    GeneralTextContent(
                        mainText = stringResource(R.string.your_schedule),
                        secondaryText = stringResource(R.string.next_lessons)
                    )
                    LessonList(lessons = lessons)
                }
            }
        }

        is LessonCardViewState.Error -> {
            Text(text = lessonState.message)
        }
    }
}

@Composable
fun LessonList(
    modifier: Modifier = Modifier,
    lessons: List<LessonCard>
) {
    Log.d("LessonList", "Lessons to display: $lessons")

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
    items: List<SubjectCard>,
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


@Preview
@Composable
fun MainScreenPreview() {
    MainScreen()
}

