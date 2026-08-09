package net.wetheGoverned.core

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

interface DispatcherProvider {
    fun io(): CoroutineContext
    fun main(): CoroutineContext
    fun default(): CoroutineContext
}

class DefaultDispatcherProvider constructor() : DispatcherProvider {
    override fun io() = Dispatchers.Default 
    override fun main() = Dispatchers.Main
    override fun default() = Dispatchers.Default
}
