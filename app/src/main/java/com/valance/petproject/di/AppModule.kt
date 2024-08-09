package com.valance.petproject.di

import com.google.firebase.database.FirebaseDatabase
import com.valance.petproject.data.FirebaseDataSource
import com.valance.petproject.data.repository.LessonCardRepositoryImpl
import com.valance.petproject.data.repository.SubjectCardRepositoryImpl
import com.valance.petproject.domain.repository.LessonCardRepository
import com.valance.petproject.domain.repository.SubjectCardRepository
import com.valance.petproject.domain.usecase.GetLessonCardUseCase
import com.valance.petproject.domain.usecase.GetSubjectCardUseCase
import org.koin.dsl.module

val appModule = module {
    single { FirebaseDatabase.getInstance() }
    single { FirebaseDataSource.FirebaseLessonCardDataSource(get()) }
    single { FirebaseDataSource.FirebaseSubjectCardDataSource(get()) }

    single<LessonCardRepository> { LessonCardRepositoryImpl(get()) }
    single<SubjectCardRepository> { SubjectCardRepositoryImpl(get()) }

    single { GetLessonCardUseCase(get()) }
    single { GetSubjectCardUseCase(get()) }

}
