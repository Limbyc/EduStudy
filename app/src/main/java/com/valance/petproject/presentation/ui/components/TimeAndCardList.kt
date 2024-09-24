package com.valance.petproject.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.valance.petproject.domain.model.LessonCard
import java.time.LocalDate

@Composable
fun TimeAndCardList(
    lessons: List<LessonCard>,
    modifier: Modifier = Modifier,
    currentDate: LocalDate
) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        lessons.forEach  { lesson ->
            Log.d("TimeAndCardList",currentDate.toString())
            TimeAndCard(currentDate = currentDate, lesson = lesson, modifier = modifier)
        }
    }
}

