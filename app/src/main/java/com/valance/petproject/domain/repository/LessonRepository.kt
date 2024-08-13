package com.valance.petproject.domain.repository

import com.valance.petproject.domain.model.LessonCard

interface LessonRepository {
    suspend fun getLessonCard(cardId: String): List<LessonCard>
}