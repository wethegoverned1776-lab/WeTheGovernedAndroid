package net.wetheGoverned.data.local

/**
 * Minimal expect for cross-platform repository references.
 * On JVM/Native platforms, this is implemented as an abstract RoomDatabase.
 * On Web platforms, it is an empty stub.
 */
expect abstract class AppDatabase {
    // Basic structural methods could be added here if needed in common
}

/**
 * Returns a platform-specific database builder.
 * On Room-supported platforms, this returns RoomDatabase.Builder<RoomAppDatabase>.
 * On Web, it returns a stub.
 */
expect fun getDatabaseBuilder(): Any
