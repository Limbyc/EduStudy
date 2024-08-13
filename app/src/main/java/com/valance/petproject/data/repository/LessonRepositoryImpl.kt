package com.valance.petproject.data.repository

import com.valance.petproject.data.datasources.LessonDataSource
import com.valance.petproject.domain.model.LessonCard
import com.valance.petproject.domain.repository.LessonRepository

class LessonRepositoryImpl(private val dataSource: LessonDataSource) :
    LessonRepository {
    override suspend fun getLessonCard(cardId: String): List<LessonCard> {
        return dataSource.getLessonCard(cardId)
    }
}