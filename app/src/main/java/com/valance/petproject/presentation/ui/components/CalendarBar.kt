    package com.valance.petproject.presentation.ui.components

    import android.util.Log
    import androidx.compose.foundation.background
    import androidx.compose.foundation.border
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.gestures.detectHorizontalDragGestures
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.Spacer
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.width
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.runtime.*
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.graphics.Color
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
    fun CalendarBar(
        initialWeekStartDate: LocalDate,
        selectedDate: LocalDate?,
        onDateSelected: (LocalDate) -> Unit,
        onWeekStartDateChange: (LocalDate) -> Unit
    ) {
        var currentWeekStartDate by remember { mutableStateOf(initialWeekStartDate) }
        val weekWithDates = getWeekWithDates(currentWeekStartDate)

        Column {
            CalendarCard(
                weekWithDates = weekWithDates,
                selectedDate = selectedDate,
                onSwipe = { direction ->
                    currentWeekStartDate = when (direction) {
                        SwipeDirection.LEFT -> currentWeekStartDate.plusWeeks(1)
                        SwipeDirection.RIGHT -> currentWeekStartDate.minusWeeks(1)
                    }
                    onWeekStartDateChange(currentWeekStartDate)
                },
                onDateSelected = { date ->
                    onDateSelected(date)
                }
            )
        }
    }


    @Composable
    fun CalendarCard(
        modifier: Modifier = Modifier,
        weekWithDates: List<DayWithDate>,
        selectedDate: LocalDate?,
        onSwipe: (SwipeDirection) -> Unit,
        onDateSelected: (LocalDate) -> Unit
    ) {
        var startPosition by remember { mutableStateOf(0f) }
        var isSwiping by remember { mutableStateOf(false) }


        val currentDate = LocalDate.now()


        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp, top = 14.dp, bottom = 14.dp)
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragStart = { startPoint ->
                            startPosition = startPoint.x
                            isSwiping = true
                        },
                        onDragEnd = {
                            isSwiping = false
                        },
                        onHorizontalDrag = { change, _ ->
                            val distance = change.position.x - startPosition
                            val threshold = 100.dp.toPx()

                            if (isSwiping) {
                                if (distance < -threshold) {
                                    onSwipe(SwipeDirection.LEFT)
                                    isSwiping = false
                                } else if (distance > threshold) {
                                    onSwipe(SwipeDirection.RIGHT)
                                    isSwiping = false
                                }
                            }
                        }
                    )
                }
        ) {
            weekWithDates.forEachIndexed { index, dayWithDate ->
                val isCurrentDate = dayWithDate.date.isEqual(currentDate)
                val isSelectedDate = selectedDate?.isEqual(dayWithDate.date) ?: false

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .clip(RoundedCornerShape(16.dp))
                        .background(
                            color = when {
                                isCurrentDate -> com.valance.petproject.presentation.ui.theme.SelectedDateColor
                                isSelectedDate -> Color.Transparent
                                else -> Color.White
                            }
                        )
                        .border(
                            width = if (isSelectedDate) 1.dp else 0.dp,
                            color = if (isSelectedDate) Color.Yellow else Color.Transparent,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .clickable { onDateSelected(dayWithDate.date) },
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CalendarDayText(
                        modifier = modifier
                            .padding(top = 4.dp, bottom = 4.dp),
                        text = dayWithDate.dayOfWeek.shortName,
                        color = when {
                            isCurrentDate -> Color.White
                            isSelectedDate -> com.valance.petproject.presentation.ui.theme.CalendarDateText
                            else -> com.valance.petproject.presentation.ui.theme.CalendarDayText
                        }
                    )
                    CalendarDateText(
                        modifier = modifier
                            .padding(top = 4.dp, bottom = 4.dp),
                        text = dayWithDate.date.dayOfMonth.toString(),
                        color = when {
                            isCurrentDate -> Color.White

                            isSelectedDate -> com.valance.petproject.presentation.ui.theme.CalendarDateText
                            else -> com.valance.petproject.presentation.ui.theme.CalendarDateText
                        }
                    )
                }
                if (index < weekWithDates.size - 1) {
                    Spacer(modifier = Modifier.width(16.dp))
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

    }
