package com.valance.petproject.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShimmeringLessonList(
    modifier: Modifier = Modifier,
    ){
    Column {
        repeat(2) {
            LessonCardShimmer(
                modifier = modifier
                    .padding(
                        end = 24.dp,
                        bottom = 8.dp,
                        start = 0.dp,
                        top = 12.dp
                    )
            )
        }
    }
}