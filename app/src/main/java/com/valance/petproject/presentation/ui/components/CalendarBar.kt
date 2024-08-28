package com.valance.petproject.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valance.petproject.data.localdata.DayWithDate
import com.valance.petproject.domain.DayOfWeek

@Composable
fun CalendarBar(){
 CalendarCard()
}

@Composable
fun CalendarCard(
    modifier: Modifier = Modifier
){
    val weekWithDates = getWeekWithDates()

    Row(
        modifier = modifier
        .fillMaxWidth()
        .padding(
            start = 15.dp,
            end = 15.dp,
            top = 14.dp,
            bottom = 14.dp
        )
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


fun getWeekWithDates(): List<DayWithDate> {
    val dates = getCurrentWeekDates()
    return DayOfWeek.entries.toTypedArray().zip(dates) { day, date -> DayWithDate(day, date) }
}


@Preview
@Composable
fun CalendarCardPreview(){
    CalendarBar()
}