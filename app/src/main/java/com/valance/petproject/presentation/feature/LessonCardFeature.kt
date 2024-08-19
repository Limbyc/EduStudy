package com.valance.petproject.presentation.feature

import android.util.Log
import com.valance.petproject.domain.usecase.GetLessonCardUseCase
import com.valance.petproject.presentation.action.LessonCardAction
import com.valance.petproject.presentation.intent.LessonCardIntent
import com.valance.petproject.presentation.reducer.LessonCardReducer
import com.valance.petproject.presentation.state.LessonCardState
import com.valance.petproject.presentation.state.LessonCardViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LessonCardFeature(
    private val getLessonCardUseCase: GetLessonCardUseCase,
    private val reducer: LessonCardReducer
) {
    private val _state = MutableStateFlow<LessonCardViewState>(LessonCardViewState.Loading)
    val viewStateFlow: StateFlow<LessonCardViewState> = _state.asStateFlow()

    private var state: LessonCardState = LessonCardState()

    private val scope = CoroutineScope(Dispatchers.Main)

    fun onAction(intent: LessonCardIntent) {
        when (intent) {
            is LessonCardIntent.LoadLessonCard -> {
                val action = LessonCardAction.Init
                reduceAndHandleSideEffects(action)
                loadLessonCard(intent.cardId)
            }
        }
    }

    private fun reduceAndHandleSideEffects(action: LessonCardAction) {
        val newState = reducer.applyAction(action, state)
        _state.value = createViewState(newState)

        when (action) {
            is LessonCardAction.QueryChanged -> loadLessonCard(action.query)
            LessonCardAction.Init -> {
                Log.d("LessonCardFeature", "Init action")
            }
            is LessonCardAction.LessonsLoaded -> {
                Log.d("LessonCardFeature", "Lessons loaded: ${action.lessons}")
            }
            is LessonCardAction.LoadError -> {
                Log.d("LessonCardFeature", "Load error: ${action.error}")
            }
        }
    }

    private fun loadLessonCard(cardId: String) {
        scope.launch {
            try {
                val lessonCards = getLessonCardUseCase.execute(cardId)
                reduceAndHandleSideEffects(LessonCardAction.LessonsLoaded(lessonCards))
            } catch (e: Exception) {
                reduceAndHandleSideEffects(LessonCardAction.LoadError(e.message ?: "Unknown error"))
            }
        }
    }

    private fun createViewState(state: LessonCardState): LessonCardViewState {
        return when {
            state.isLoading -> LessonCardViewState.Loading
            !state.error.isNullOrBlank() -> LessonCardViewState.Error(state.error)
            else -> LessonCardViewState.Success(state.item)
        }
    }
}
