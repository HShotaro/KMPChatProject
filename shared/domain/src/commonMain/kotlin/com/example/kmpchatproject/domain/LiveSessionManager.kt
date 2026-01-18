package com.example.kmpchatproject.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * 特定のルームのライブセッション状態を管理するクラスです。
 * ViewModelから呼び出され、UIにライブ状態を配信することを想定しています。
 *
 * @property roomId セッションを識別するためのルームID
 */
class LiveSessionManager(val roomId: String) {
    private val _isOnLive = MutableStateFlow(false)
    val isOnLive = _isOnLive.asStateFlow()

    fun setLiveStatus(active: Boolean) {
        _isOnLive.value = active
    }
}
