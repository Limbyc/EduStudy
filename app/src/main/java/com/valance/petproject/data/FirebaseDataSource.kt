package com.valance.petproject.data

import com.google.firebase.database.FirebaseDatabase
import com.valance.petproject.domain.model.LessonCard
import com.valance.petproject.domain.model.SubjectCard
import kotlinx.coroutines.tasks.await

class FirebaseDataSource(private val database: FirebaseDatabase) {

    class FirebaseLessonCardDataSource(private val database: FirebaseDatabase) {
        suspend fun getLessonCard(cardId: String): LessonCard {
            val reference = database.getReference("lessonCard/$cardId")
            val snapshot = reference.get().await()
            return snapshot.getValue(LessonCard::class.java) ?: throw Exception("Card not found")
        }
    }

    class FirebaseSubjectCardDataSource(private val database: FirebaseDatabase) {
        suspend fun getSubjectCard(cardId: String): SubjectCard {
            val reference = database.getReference("subjectCard/$cardId")
            val snapshot = reference.get().await()
            return snapshot.getValue(SubjectCard::class.java) ?: throw Exception("Card not found")
        }
    }

}
