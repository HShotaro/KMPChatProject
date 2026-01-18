package com.example.kmpchatproject.uimodel

import androidx.lifecycle.ViewModel
import com.example.kmpchatproject.domain.SampleUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SampleViewModel(private val sampleUseCase: SampleUseCase) : ViewModel() {
    private val _uiState = MutableStateFlow("Initial State")
    val uiState: StateFlow<String> = _uiState.asStateFlow()

    fun loadData() {
        _uiState.value = sampleUseCase.execute()
    }
}
