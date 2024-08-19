package com.valance.petproject.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valance.petproject.domain.model.SubjectCard

@Composable
fun SubjectList(
    modifier: Modifier = Modifier,
    items: List<SubjectCard>,
) {
    LazyRow(
        modifier = modifier.padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(items) { item ->
            CardSubject(item = item)
        }
    }
}
