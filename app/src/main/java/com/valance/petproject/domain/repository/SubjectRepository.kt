package com.valance.petproject.domain.repository

import com.valance.petproject.domain.model.SubjectCard

interface SubjectRepository {
    suspend fun getSubjectCard(cardId: String): List<SubjectCard>
}