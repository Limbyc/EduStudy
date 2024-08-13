package com.valance.petproject.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valance.petproject.domain.usecase.GetSubjectCardUseCase
import com.valance.petproject.presentation.intent.LessonCardIntent
import com.valance.petproject.presentation.intent.SubjectCardIntent
import com.valance.petproject.presentation.state.LessonCardViewState
import com.valance.petproject.presentation.state.SubjectCardViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SubjectCardViewModel(private val getSubjectCardUseCase: GetSubjectCardUseCase) : ViewModel() {

    private val _state = MutableStateFlow<SubjectCardViewState>(SubjectCardViewState.Loading)
    val state: StateFlow<SubjectCardViewState> = _state

    init{
        handleIntent(SubjectCardIntent.LoadLessonCard(""))
    }

    fun handleIntent(intent: SubjectCardIntent) {
        when (intent) {
            is SubjectCardIntent.LoadLessonCard -> loadSubjectCard(intent.cardId)
        }
    }

    private fun loadSubjectCard(cardId: String) {
        viewModelScope.launch {
            _state.value = SubjectCardViewState.Loading
            try {
                val subjectCards = getSubjectCardUseCase.execute(cardId)
                _state.value = SubjectCardViewState.Success(subjectCards)
            } catch (e: Exception) {
                _state.value = SubjectCardViewState.Error(e.message ?: "Unknown error")
            }
        }
    }
}