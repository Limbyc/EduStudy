package com.valance.petproject.presentation.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ShimmeringSubjectList(
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier
        .padding(top = 16.dp)) {
        repeat(3) {
            SubjectCardShimmer(
                modifier = modifier
                    .padding(end = 8.dp)
            )
        }
    }
}