package com.valance.petproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valance.petproject.domain.usecase.GetLessonCardUseCase
import com.valance.petproject.presentation.intent.LessonCardIntent
import com.valance.petproject.presentation.state.LessonCardViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LessonCardViewModel(private val getLessonCardUseCase: GetLessonCardUseCase) : ViewModel() {

    private val _state = MutableStateFlow<LessonCardViewState>(LessonCardViewState.Loading)
    val state: StateFlow<LessonCardViewState> = _state

    init{
        handleIntent(LessonCardIntent.LoadLessonCard(""))
    }

    fun handleIntent(intent: LessonCardIntent) {
        when (intent) {
            is LessonCardIntent.LoadLessonCard -> loadLessonCard(intent.cardId)
        }
    }

    private fun loadLessonCard(cardId: String) {
        viewModelScope.launch {
            _state.value = LessonCardViewState.Loading
            try {
                val lessonCards = getLessonCardUseCase.execute(cardId)
                _state.value = LessonCardViewState.Success(lessonCards)
            } catch (e: Exception) {
                _state.value = LessonCardViewState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
