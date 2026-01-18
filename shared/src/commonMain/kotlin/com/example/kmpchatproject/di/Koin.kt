package com.example.kmpchatproject.di

import com.example.kmpchatproject.data.SampleRepositoryImpl
import com.example.kmpchatproject.domain.LiveSessionManager
import com.example.kmpchatproject.domain.SampleRepository
import com.example.kmpchatproject.domain.SampleUseCase
import com.example.kmpchatproject.uimodel.SampleViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val commonModule = module {
    // ID (named) で管理するように変更
    single<SampleRepository>(named("DefaultRepo")) { SampleRepositoryImpl() }
    
    factory(named("DefaultUseCase")) { 
        SampleUseCase(repository = get(named("DefaultRepo"))) 
    }
    
    factory(named("DefaultViewModel")) { 
        SampleViewModel(sampleUseCase = get(named("DefaultUseCase"))) 
    }

    // LiveSessionManager の追加 (ルームIDをパラメータで受け取って生成)
    factory { (roomId: String) -> LiveSessionManager(roomId) }
}

fun initKoin(appDeclaration: org.koin.dsl.KoinAppDeclaration = {}) =
    org.koin.core.context.startKoin {
        appDeclaration()
        modules(commonModule)
    }

// iOS initialization
fun initKoin() = initKoin {}
