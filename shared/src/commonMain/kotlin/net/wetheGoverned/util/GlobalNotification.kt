package net.wetheGoverned.util

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object GlobalNotification {
    private val _messages = MutableSharedFlow<String>(extraBufferCapacity = 10)
    val messages: SharedFlow<String> = _messages.asSharedFlow()

    fun notify(message: String) {
        println("📢 NOTIFICATION: $message")
        _messages.tryEmit(message)
    }
}
