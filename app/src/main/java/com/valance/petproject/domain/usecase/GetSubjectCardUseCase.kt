package com.valance.petproject.domain.usecase

import com.valance.petproject.domain.model.SubjectCard
import com.valance.petproject.domain.repository.SubjectRepository

class GetSubjectCardUseCase(private val repository: SubjectRepository) {
    suspend fun execute(cardId: String): List<SubjectCard> {
        return repository.getSubjectCard(cardId)
    }
}