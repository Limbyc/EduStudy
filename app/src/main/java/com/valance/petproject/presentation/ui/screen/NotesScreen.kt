package com.valance.petproject.presentation.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valance.petproject.R
import com.valance.petproject.presentation.state.LessonCardViewState
import com.valance.petproject.presentation.ui.components.ButtonDate
import com.valance.petproject.presentation.ui.components.CalendarBar
import com.valance.petproject.presentation.ui.components.LessonList
import com.valance.petproject.presentation.ui.components.SecondaryText
import com.valance.petproject.presentation.ui.components.ShimmeringLessonList
import com.valance.petproject.presentation.ui.components.TimeAndCard
import com.valance.petproject.presentation.ui.components.TimeAndCardList
import com.valance.petproject.presentation.viewmodel.LessonCardViewModel
import org.jetbrains.annotations.Async
import org.koin.androidx.compose.getViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun NotesScreen() {

    val lessonCardViewModel: LessonCardViewModel = getViewModel()
    val state by lessonCardViewModel.state.collectAsState()

    Calendar(state = state)
    CurrentDate()
}

@Composable
fun CurrentDate(
    modifier: Modifier = Modifier,
) {
    val currentDate = LocalDate.now()

    val dayFormatter = DateTimeFormatter.ofPattern("dd")
    val dayText = currentDate.format(dayFormatter)

    val dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH)
    val dayOfWeekText = currentDate.format(dayOfWeekFormatter)

    val monthYearFormatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH)
    val monthYearText = currentDate.format(monthYearFormatter)

    Row(
        modifier = modifier
            .padding(
                top = 32.dp,
                start = 28.dp,
                end = 28.dp,
                bottom = 14.dp
            ),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(
            modifier = modifier,
            text = dayText,
            fontSize = 44.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Column(
            modifier = modifier
                .padding(start = 8.dp)
        ) {
            SecondaryText(text = dayOfWeekText)
            SecondaryText(text = monthYearText)
        }

        Spacer(modifier = modifier.weight(1f))

        ButtonDate()
    }
}

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    state: LessonCardViewState,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 110.dp)
            .clip(shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
            .background(color = Color.White)
            .padding(top = 10.dp)
    ) {
        Column {
            CalendarBar()

            HorizontalDivider(
                color = com.valance.petproject.presentation.ui.theme.LineColor,
                thickness = 1.dp
            )

            Schedule(lessonState = state)
        }
    }
}

@Composable
fun Schedule(
    modifier: Modifier = Modifier,
    lessonState: LessonCardViewState,
) {
    Column(
        modifier = modifier
            .padding(
                start = 28.dp,
                end = 28.dp,
                top = 14.dp,
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        Row(verticalAlignment = Alignment.CenterVertically)
        {
            SecondaryText(text = stringResource(R.string.time))

            Spacer(modifier = Modifier.width(34.dp))

            SecondaryText(text = stringResource(R.string.course))

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(R.drawable.lesson_list),
                contentDescription = null
            )
        }

        when (lessonState) {
            is LessonCardViewState.Loading -> {
                CircularProgressIndicator()
            }

            is LessonCardViewState.Success -> {
                TimeAndCardList(lessons = lessonState.lessons)
            }

            is LessonCardViewState.Error -> {
                Text(
                    text = lessonState.message,
                    color = Color.Red,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

    }
}


@Preview
@Composable
fun CurrentDatePreview() {
    NotesScreen()
}