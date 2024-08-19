package com.valance.petproject.presentation.ui.components

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valance.petproject.domain.model.LessonCard

@Composable
fun LessonList(
    modifier: Modifier = Modifier,
    lessons: List<LessonCard>
) {
    Log.d("LessonList", "Lessons to display: $lessons")

    Column(
        modifier = modifier
            .padding(end = 28.dp, top = 16.dp)
            .fillMaxWidth()
    ) {
        lessons.forEach { lesson ->
            CardLesson(lesson = lesson)
        }
    }
}