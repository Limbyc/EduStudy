package com.valance.petproject.domain.usecase

import com.valance.petproject.domain.model.LessonCard
import com.valance.petproject.domain.repository.LessonRepository

class GetLessonCardUseCase(private val repository: LessonRepository) {
    suspend fun execute(cardId: String): List<LessonCard> {
        return repository.getLessonCard(cardId)
    }
}