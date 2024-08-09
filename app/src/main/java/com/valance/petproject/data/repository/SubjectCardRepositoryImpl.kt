package com.valance.petproject.data.repository

import com.valance.petproject.data.FirebaseDataSource
import com.valance.petproject.domain.model.LessonCard
import com.valance.petproject.domain.model.SubjectCard
import com.valance.petproject.domain.repository.LessonCardRepository
import com.valance.petproject.domain.repository.SubjectCardRepository

class LessonCardRepositoryImpl(private val dataSource: FirebaseDataSource.FirebaseLessonCardDataSource) :
    LessonCardRepository {
    override suspend fun getLessonCard(cardId: String): LessonCard {
        return dataSource.getLessonCard(cardId)
    }
}

class SubjectCardRepositoryImpl(private val dataSource: FirebaseDataSource.FirebaseSubjectCardDataSource) :
    SubjectCardRepository {
    override suspend fun getSubjectCard(cardId: String): SubjectCard {
        return dataSource.getSubjectCard(cardId)
    }
}
