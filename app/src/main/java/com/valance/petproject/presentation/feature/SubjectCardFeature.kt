package com.valance.petproject.presentation.feature

import android.util.Log
import com.valance.petproject.domain.usecase.GetSubjectCardUseCase
import com.valance.petproject.presentation.action.SubjectCardAction
import com.valance.petproject.presentation.intent.SubjectCardIntent
import com.valance.petproject.presentation.reducer.SubjectCardReducer
import com.valance.petproject.presentation.state.SubjectCardState
import com.valance.petproject.presentation.state.SubjectCardViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SubjectCardFeature(
    private val getSubjectCardUseCase: GetSubjectCardUseCase,
    private val reducer: SubjectCardReducer
) {
    private val _state = MutableStateFlow<SubjectCardViewState>(SubjectCardViewState.Loading)
    val viewStateFlow: StateFlow<SubjectCardViewState> = _state.asStateFlow()

    private var state: SubjectCardState = SubjectCardState()

    private val scope = CoroutineScope(Dispatchers.Main)

    fun onAction(intent: SubjectCardIntent) {
        when (intent) {
            is SubjectCardIntent.LoadLessonCard -> {
                val action = SubjectCardAction.Init
                reduceAndHandleSideEffects(action)
                loadSubjectCard(intent.cardId)
            }
        }
    }

    private fun reduceAndHandleSideEffects(action: SubjectCardAction) {
        val newState = reducer.applyAction(action, state)
        _state.value = createViewState(newState)

        when (action) {
            is SubjectCardAction.QueryChanged -> loadSubjectCard(action.query)
            SubjectCardAction.Init -> {
                Log.d("SubjectCardFeature", "Init action")
            }
            is SubjectCardAction.LoadError -> {
                Log.d("SubjectCardFeature", "Load error: ${action.error}")
            }
            is SubjectCardAction.SubjectLoaded -> {
                Log.d("SubjectCardFeature", "Subject loaded: ${action.subject}")
            }
        }
    }

    private fun loadSubjectCard(cardId: String) {
        scope.launch {
            try {
                val subjectCards = getSubjectCardUseCase.execute(cardId)
                reduceAndHandleSideEffects(SubjectCardAction.SubjectLoaded(subjectCards))
            } catch (e: Exception) {
                reduceAndHandleSideEffects(SubjectCardAction.LoadError(e.message ?: "Unknown error"))
            }
        }
    }

    private fun createViewState(state: SubjectCardState): SubjectCardViewState {
        return when {
            state.isLoading -> SubjectCardViewState.Loading
            !state.error.isNullOrBlank() -> SubjectCardViewState.Error(state.error)
            else -> SubjectCardViewState.Success(state.item)
        }
    }
}
