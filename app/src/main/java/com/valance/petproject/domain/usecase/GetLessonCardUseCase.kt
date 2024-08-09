package com.valance.petproject.domain.usecase

import com.valance.petproject.domain.model.LessonCard
import com.valance.petproject.domain.repository.LessonCardRepository

class GetLessonCardUseCase(private val repository: LessonCardRepository) {
    suspend fun execute(cardId: String): LessonCard {
        return repository.getLessonCard(cardId)
    }
}