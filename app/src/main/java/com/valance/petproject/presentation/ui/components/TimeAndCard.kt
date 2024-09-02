package com.valance.petproject.presentation.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.valance.petproject.domain.model.LessonCard
import com.valance.petproject.presentation.viewmodel.LessonCardViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun TimeAndCard(
    modifier: Modifier = Modifier,
    lesson: LessonCard
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TimeLessonStartText(
                modifier = modifier.padding(bottom = 6.dp), lesson.timeStart
            )
            TimeLessonEndText(lesson.timeEnd)
        }


        VerticalDivider(
            modifier = modifier.padding(start = 16.dp, end = 16.dp),
            color = com.valance.petproject.presentation.ui.theme.LineColor,
            thickness = 1.dp
        )


        CardLesson(
            lesson = lesson
        )
    }
}



@Preview
@Composable
fun TimeAndCardPreview(){
    TimeAndCard(lesson = LessonCard(
        "title", "theme", "place", "teacher",
        "https://www.simplilearn.com/ice9/free_resources_article_thumb/what_is_image_Processing.jpg","","",""
    ))
}