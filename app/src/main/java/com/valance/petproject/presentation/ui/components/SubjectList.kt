package com.valance.petproject.presentation.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import com.valance.petproject.domain.model.SubjectCard

@Composable
fun SubjectList(
    modifier: Modifier = Modifier,
    items: List<SubjectCard>,
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    LazyRow(
        state = listState,
        modifier = modifier.padding(top = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(items) { item ->
            CardSubject(item = item)
        }
    }

    LaunchedEffect(listState.isScrollInProgress) {
        if (!listState.isScrollInProgress) {
            coroutineScope.launch {
                val firstVisibleIndex = listState.firstVisibleItemIndex
                val visibleItems = listState.layoutInfo.visibleItemsInfo
                val viewportStart = listState.layoutInfo.viewportStartOffset
                val viewportEnd = listState.layoutInfo.viewportEndOffset

                if (visibleItems.size >= 2) {
                    val firstItem = visibleItems[0]
                    val thirdItem = visibleItems.getOrNull(2)

                    val firstItemOffset = firstItem.offset
                    val firstItemEnd = firstItem.offset + firstItem.size
                    val firstItemVisiblePercent = (firstItemEnd - viewportStart) / firstItem.size.toFloat()

                    val thirdItemVisiblePercent = thirdItem?.let {
                        val thirdItemStart = it.offset
                        val thirdItemEnd = it.offset + it.size
                        val visibleStart = maxOf(thirdItemStart, viewportStart)
                        val visibleEnd = minOf(thirdItemEnd, viewportEnd)
                        (visibleEnd - visibleStart) / it.size.toFloat()
                    } ?: 0f

                    if (firstItemOffset < viewportStart && firstItemVisiblePercent > 0.5) {
                        listState.animateScrollToItem(firstVisibleIndex)
                    }
                    else if (thirdItem != null && thirdItemVisiblePercent > 0.7) {
                        listState.animateScrollToItem(firstVisibleIndex + 1)
                    }
                }
            }
        }
    }
}
