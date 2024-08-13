package com.valance.petproject.data.repository

import com.valance.petproject.data.datasources.SubjectDataSource
import com.valance.petproject.domain.model.SubjectCard
import com.valance.petproject.domain.repository.SubjectRepository

class SubjectRepositoryImpl(private val dataSource: SubjectDataSource) :
    SubjectRepository {
    override suspend fun getSubjectCard(cardId: String): List<SubjectCard> {
        return dataSource.getSubjectCard(cardId)
    }
}
