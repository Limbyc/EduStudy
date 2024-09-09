package com.valance.petproject.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.valance.petproject.domain.model.LessonCard

@Composable
fun TimeAndCardList(
    lessons: List<LessonCard>,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        lessons.forEach  { lesson ->
            TimeAndCard(lesson = lesson, modifier = modifier)
        }
    }
}

