package net.wetheGoverned.core

interface CivicPublisher {
    suspend fun signPublishImportCivicEvent(kind: Int, content: String, tags: List<List<String>> = emptyList()): String?
}
