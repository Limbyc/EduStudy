package com.valance.petproject.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GeneralTextContent(
    modifier: Modifier = Modifier,
    mainText: String? = null,
    secondaryText: String? = null,
){
    Column(modifier = modifier) {
        mainText?.let {
            MainText(
                modifier = modifier.padding(bottom = 5.dp),
                text = it,
            )
        }

        secondaryText?.let { SecondaryText(text = it) }
    }
}

@Composable
fun MainText(
    modifier: Modifier = Modifier,
    text: String,
){
    Text(
        modifier = modifier,
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = Color.Black,
    )
}

@Composable
fun SecondaryText(
    modifier: Modifier = Modifier,
    text: String,
){
    Text(
        modifier = modifier,
        text = text,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        color = com.valance.petproject.presentation.ui.theme.NameTextColor,
    )
}

@Composable
fun CurrentDateText(
    modifier: Modifier = Modifier,
    text: String,
){
    Text(
        modifier = modifier,
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        color = com.valance.petproject.presentation.ui.theme.ButtonDateText
    )
}

@Composable
fun CalendarDayText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color
){
    Text(
        modifier = modifier,
        text = text,
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        color = color
    )
}

@Composable
fun CalendarDateText(
    modifier: Modifier = Modifier,
    text: String,
    color: Color
){
    Text(
        modifier = modifier,
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        color = color
    )
}

@Composable
fun TimeLessonStartText(
    modifier: Modifier = Modifier,
    text: String,
){
    Text(
        modifier = modifier,
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        color = com.valance.petproject.presentation.ui.theme.TimeStartLesson
    )
}

@Composable
fun TimeLessonEndText(
    text: String,
){
    Text(
        text = text,
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        color = com.valance.petproject.presentation.ui.theme.TimeEndLesson
    )
}