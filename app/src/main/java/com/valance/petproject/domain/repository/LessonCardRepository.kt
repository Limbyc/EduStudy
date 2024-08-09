package com.valance.petproject.domain.repository

import com.valance.petproject.domain.model.LessonCard

interface LessonCardRepository {
    suspend fun getLessonCard(cardId: String): LessonCard
}