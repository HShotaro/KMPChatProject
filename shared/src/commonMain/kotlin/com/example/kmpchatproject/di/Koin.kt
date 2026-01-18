package com.example.kmpchatproject.di

import com.example.kmpchatproject.data.SampleRepositoryImpl
import com.example.kmpchatproject.domain.SampleRepository
import com.example.kmpchatproject.domain.SampleUseCase
import com.example.kmpchatproject.uimodel.SampleViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonModule = module {
    singleOf(::SampleRepositoryImpl) bind SampleRepository::class
    factoryOf(::SampleUseCase)
    factoryOf(::SampleViewModel)
}

fun initKoin(appDeclaration: org.koin.dsl.KoinAppDeclaration = {}) =
    org.koin.core.context.startKoin {
        appDeclaration()
        modules(commonModule)
    }

// iOS initialization
fun initKoin() = initKoin {}
