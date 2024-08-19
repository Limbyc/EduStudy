package com.valance.petproject.di

import com.google.firebase.database.FirebaseDatabase
import com.valance.petproject.data.datasources.LessonDataSource
import com.valance.petproject.data.datasources.SubjectDataSource
import com.valance.petproject.data.repository.LessonRepositoryImpl
import com.valance.petproject.data.repository.SubjectRepositoryImpl
import com.valance.petproject.domain.repository.LessonRepository
import com.valance.petproject.domain.repository.SubjectRepository
import com.valance.petproject.domain.usecase.GetLessonCardUseCase
import com.valance.petproject.domain.usecase.GetSubjectCardUseCase
import com.valance.petproject.presentation.feature.LessonCardFeature
import com.valance.petproject.presentation.feature.SubjectCardFeature
import com.valance.petproject.presentation.reducer.LessonCardReducer
import com.valance.petproject.presentation.reducer.SubjectCardReducer
import com.valance.petproject.presentation.viewmodel.LessonCardViewModel
import com.valance.petproject.presentation.viewmodel.SubjectCardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<FirebaseDatabase> { FirebaseDatabase.getInstance() }
    single<LessonDataSource> { LessonDataSource(get()) }
    single<SubjectDataSource> { SubjectDataSource(get()) }

    single<LessonCardReducer> { LessonCardReducer() }
    single<SubjectCardReducer> { SubjectCardReducer() }
    single<LessonRepository> { LessonRepositoryImpl(get()) }
    single<SubjectRepository> { SubjectRepositoryImpl(get()) }

    single<GetLessonCardUseCase> { GetLessonCardUseCase(get()) }
    single<GetSubjectCardUseCase> { GetSubjectCardUseCase(get()) }

    single<LessonCardFeature>{ LessonCardFeature(get(), get()) }
    single<SubjectCardFeature> { SubjectCardFeature(get(), get()) }

    viewModel<LessonCardViewModel> { LessonCardViewModel(get()) }
    viewModel<SubjectCardViewModel> {SubjectCardViewModel(get())}
}
