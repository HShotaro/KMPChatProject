package com.example.kmpchatproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ChatMessage(
    val role: String,
    val message: String
)

class ChatViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<List<ChatMessage>>(emptyList())
    val uiState: StateFlow<List<ChatMessage>> = _uiState.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY
    )

    private val chat = generativeModel.startChat()

    fun sendMessage(userMessage: String) {
        if (userMessage.isBlank()) return

        val newMessage = ChatMessage(role = "user", message = userMessage)
        _uiState.value = _uiState.value + newMessage
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val response = chat.sendMessage(userMessage)
                val assistantMessage = ChatMessage(
                    role = "model",
                    message = response.text ?: "エラーが発生しました"
                )
                _uiState.value = _uiState.value + assistantMessage
            } catch (e: Exception) {
                _uiState.value = _uiState.value + ChatMessage(
                    role = "model",
                    message = "エラー: ${e.localizedMessage}"
                )
            } finally {
                _isLoading.value = false
            }
        }
    }
}
