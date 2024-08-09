package com.valance.petproject.domain.repository

import com.valance.petproject.domain.model.SubjectCard

interface SubjectCardRepository {
    suspend fun getSubjectCard(cardId: String): SubjectCard
}