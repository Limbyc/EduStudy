package com.valance.petproject.data.datasources

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.getValue
import com.google.gson.Gson
import com.valance.petproject.domain.model.LessonCard
import kotlinx.coroutines.tasks.await

class LessonDataSource(private val database: FirebaseDatabase) {

    suspend fun getLessonCard(cardId: String): List<LessonCard> {
        val reference = database.getReference("lessonCard/$cardId")
        val snapshot = reference.get().await()

        Log.d("cards", cardId)
        val lessonCardMap = snapshot.getValue<Map<String, Map<String, Any>>>()
            ?: throw Exception("Card not found")

        return lessonCardMap.values.mapNotNull { jsonMap ->
            try {
                val json = Gson().toJson(jsonMap)
                Gson().fromJson(json, LessonCard::class.java)
            } catch (e: Exception) {
                null
            }
        }
    }
}