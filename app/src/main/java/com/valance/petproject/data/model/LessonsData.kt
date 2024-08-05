package com.valance.petproject.data.model

data class LessonsData(
    val name: String,
    val theme: String,
    val place: String,
    val teacher: String,
    val teacherIcon: String, // Uri from remote data
)
