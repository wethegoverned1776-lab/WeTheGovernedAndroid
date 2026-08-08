package net.wetheGoverned.session

interface PendingEventQueue {
    fun enqueue(eventJson: String)
    fun getPending(): List<String>
    fun remove(eventJson: String)
}
