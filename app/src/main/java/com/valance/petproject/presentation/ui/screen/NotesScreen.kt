package com.valance.petproject.presentation.ui.screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valance.petproject.R
import com.valance.petproject.presentation.state.LessonCardViewState
import com.valance.petproject.presentation.ui.components.*
import com.valance.petproject.presentation.viewmodel.LessonCardViewModel
import org.koin.androidx.compose.getViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun NotesScreen() {
    val viewModel: LessonCardViewModel = getViewModel()

    val currentWeekStartDate by viewModel.currentWeekStartDate.collectAsState()
    val state by viewModel.state.collectAsState()
    val initialWeekStartDate = remember { currentWeekStartDate }

    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    var visibleWeekStartDate by remember { mutableStateOf(currentWeekStartDate) }

    Calendar(
        state = state,
        currentWeekStartDate = visibleWeekStartDate,
        selectedDate = selectedDate,
        onDateSelected = { date ->
            selectedDate = date
        },
        onWeekStartDateChange = { newDate ->
            visibleWeekStartDate = newDate
            viewModel.updateWeekStartDate(newDate)
        }
    )

    CurrentDate(
        selectedDate = selectedDate,
        onTodayClick = {
            visibleWeekStartDate = initialWeekStartDate
            selectedDate = null
            viewModel.updateWeekStartDate(initialWeekStartDate)
        }
    )
}

@Composable
fun CurrentDate(
    modifier: Modifier = Modifier,
    selectedDate: LocalDate?,
    onTodayClick: () -> Unit
) {
    val currentDate = selectedDate ?: LocalDate.now()

    val dayFormatter = DateTimeFormatter.ofPattern("dd", Locale.getDefault())
    val dayText = currentDate.format(dayFormatter)

    val dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH)
    val dayOfWeekText = currentDate.format(dayOfWeekFormatter)

    val monthYearFormatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH)
    val monthYearText = currentDate.format(monthYearFormatter)

    Row(
        modifier = modifier
            .padding(top = 32.dp, start = 28.dp, end = 28.dp, bottom = 14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = modifier,
            text = dayText,
            fontSize = 44.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )

        Column(modifier = modifier.padding(start = 8.dp)) {
            SecondaryText(text = dayOfWeekText)
            SecondaryText(text = monthYearText)
        }

        Spacer(modifier = modifier.weight(1f))

        ButtonDate(onClick = onTodayClick)
    }
}

@Composable
fun Calendar(
    modifier: Modifier = Modifier,
    state: LessonCardViewState,
    currentWeekStartDate: LocalDate,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit,
    onWeekStartDateChange: (LocalDate) -> Unit
) {
    Log.d("Calendar", "Rendering with current week start date: $currentWeekStartDate")

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 110.dp)
            .clip(shape = RoundedCornerShape(topStart = 28.dp, topEnd = 28.dp))
            .background(color = Color.White)
            .padding(top = 10.dp)
    ) {
        Column {
            CalendarBar(
                initialWeekStartDate = currentWeekStartDate,
                selectedDate = selectedDate,
                onDateSelected = onDateSelected,
                onWeekStartDateChange = onWeekStartDateChange
            )

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
    lessonState: LessonCardViewState
) {
    Column(
        modifier = modifier
            .padding(start = 28.dp, end = 28.dp, top = 14.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
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
            is LessonCardViewState.Loading -> CircularProgressIndicator()

            is LessonCardViewState.Success -> TimeAndCardList(lessons = lessonState.lessons)

            is LessonCardViewState.Error -> Text(
                text = lessonState.message,
                color = Color.Red,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@Preview
@Composable
fun CurrentDatePreview() {
    NotesScreen()
}
