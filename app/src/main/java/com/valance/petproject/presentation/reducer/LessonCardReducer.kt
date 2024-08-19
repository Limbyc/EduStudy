package com.valance.petproject.presentation.reducer

import com.valance.petproject.presentation.action.LessonCardAction
import com.valance.petproject.presentation.state.LessonCardState
import com.valance.petproject.presentation.state.LessonCardViewState

class LessonCardReducer {
    fun applyAction(action: LessonCardAction, state: LessonCardState): LessonCardState {
        return when (action) {
            LessonCardAction.Init -> state.copy(isLoading = true)
            is LessonCardAction.QueryChanged -> state.copy(query = action.query, isLoading = true)
            is LessonCardAction.LessonsLoaded -> state.copy(item = action.lessons, isLoading = false)
            is LessonCardAction.LoadError -> state.copy(error = action.error, isLoading = false)
        }
    }
}