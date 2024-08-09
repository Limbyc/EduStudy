package com.valance.petproject.domain.usecase

import com.valance.petproject.domain.model.SubjectCard
import com.valance.petproject.domain.repository.SubjectCardRepository

class GetSubjectCardUseCase(private val repository: SubjectCardRepository) {
    suspend fun execute(cardId: String): SubjectCard {
        return repository.getSubjectCard(cardId)
    }
}