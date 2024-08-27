package com.valance.petproject.presentation.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valance.petproject.presentation.ui.components.ButtonDate
import com.valance.petproject.presentation.ui.components.SecondaryText
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun NotesScreen(){

    CurrentDate()
}

@Composable
fun CurrentDate(
    modifier: Modifier = Modifier
){
    val currentDate = LocalDate.now()

    val dayFormatter = DateTimeFormatter.ofPattern("dd")
    val dayText = currentDate.format(dayFormatter)

    val dayOfWeekFormatter = DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH)
    val dayOfWeekText = currentDate.format(dayOfWeekFormatter)

    val monthYearFormatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.ENGLISH)
    val monthYearText = currentDate.format(monthYearFormatter)

    Row(modifier = modifier
        .padding(
            top = 32.dp,
            start = 28.dp,
            end = 28.dp,
            bottom = 14.dp
        ),
        verticalAlignment = Alignment.CenterVertically

    ){
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

@Preview
@Composable
fun CurrentDatePreview(){
    CurrentDate()
}