package com.valance.petproject.presentation.ui.components

import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valance.petproject.data.localdata.DayWithDate
import com.valance.petproject.domain.DayOfWeek
import com.valance.petproject.domain.model.SwipeDirection
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*

@Composable
fun CalendarBar() {
    var currentWeekStartDate by remember { mutableStateOf(getCurrentWeekStartDate()) }
    val weekWithDates = getWeekWithDates(currentWeekStartDate)

    Column {
        CalendarCard(
            weekWithDates = weekWithDates,
            onSwipe = { direction ->
                currentWeekStartDate = when (direction) {
                    SwipeDirection.LEFT -> currentWeekStartDate.plusWeeks(1)
                    SwipeDirection.RIGHT -> currentWeekStartDate.minusWeeks(1)
                }
            }
        )
    }
}

@Composable
fun CalendarCard(
    modifier: Modifier = Modifier,
    weekWithDates: List<DayWithDate>,
    onSwipe: (SwipeDirection) -> Unit
) {
    var startPosition by remember { mutableStateOf(0f) }
    var isSwiping by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp, top = 14.dp, bottom = 14.dp)
            .pointerInput(Unit) {
                detectHorizontalDragGestures { change, dragAmount ->
                    if (!isSwiping) {
                        startPosition = change.position.x
                        isSwiping = true
                    }

                    val distance = change.position.x - startPosition
                    val threshold = 100.dp.toPx()

                    if (distance < -threshold) {
                        onSwipe(SwipeDirection.LEFT)
                        isSwiping = false
                    } else if (distance > threshold) {
                        onSwipe(SwipeDirection.RIGHT)
                        isSwiping = false
                    }
                }
            }
    ) {
        weekWithDates.forEach { dayWithDate ->
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CalendarDayText(dayWithDate.dayOfWeek.shortName)
                CalendarDateText(
                    modifier = modifier.padding(top = 4.dp),
                    dayWithDate.date.dayOfMonth.toString()
                )
            }
        }
    }
}


fun getCurrentWeekStartDate(): LocalDate {
    return LocalDate.now().with(WeekFields.of(Locale.getDefault()).dayOfWeek(), 1)
}

fun getWeekWithDates(startDate: LocalDate): List<DayWithDate> {
    val dates = (0..6).map { startDate.plusDays(it.toLong()) }
    return DayOfWeek.entries.toTypedArray().zip(dates) { day, date -> DayWithDate(day, date) }
}

@Preview
@Composable
fun CalendarCardPreview() {
    CalendarBar()
}
