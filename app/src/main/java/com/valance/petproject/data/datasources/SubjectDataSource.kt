package com.valance.petproject.data.datasources

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import com.google.gson.Gson
import com.valance.petproject.domain.model.LessonCard
import com.valance.petproject.domain.model.SubjectCard
import kotlinx.coroutines.tasks.await

class SubjectDataSource(private val database: FirebaseDatabase) {
        suspend fun getSubjectCard(cardId: String): List<SubjectCard> {
        val reference = database.getReference("subjectCard/$cardId")
        val snapshot = reference.get().await()

        Log.d("cards", cardId)
        val subjectCardMap = snapshot.getValue<Map<String, Map<String, Any>>>()
            ?: throw Exception("Card not found")

            return subjectCardMap.values.mapNotNull { jsonMap ->
                try {
                    val json = Gson().toJson(jsonMap)
                    Gson().fromJson(json, SubjectCard::class.java)
                } catch (e: Exception) {
                    null
                }
            }
        }
    }