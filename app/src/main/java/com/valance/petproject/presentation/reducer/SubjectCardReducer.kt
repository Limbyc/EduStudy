package com.valance.petproject.presentation.reducer

import com.valance.petproject.presentation.action.SubjectCardAction
import com.valance.petproject.presentation.state.SubjectCardState

class SubjectCardReducer {
    fun applyAction(action: SubjectCardAction, state: SubjectCardState): SubjectCardState {
        return when (action) {
            SubjectCardAction.Init -> state.copy(isLoading = true)
                is SubjectCardAction.QueryChanged -> state.copy(query = action.query, isLoading = true)
                is SubjectCardAction.SubjectLoaded -> state.copy(item = action.subject, isLoading = false)
                is SubjectCardAction.LoadError -> state.copy(error = action.error, isLoading = false)
        }
    }
}