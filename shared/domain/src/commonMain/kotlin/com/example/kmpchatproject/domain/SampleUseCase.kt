package com.example.kmpchatproject.domain

class SampleUseCase(private val repository: SampleRepository) {
    fun execute(): String {
        return "UseCase: ${repository.getData()}"
    }
}
