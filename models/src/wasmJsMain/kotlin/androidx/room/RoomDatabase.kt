package androidx.room

abstract class RoomDatabase {
    class Builder<T> {
        fun fallbackToDestructiveMigration(enabled: Boolean = true): Builder<T> = this
        fun setQueryCoroutineContext(context: kotlin.coroutines.CoroutineContext): Builder<T> = this
        fun setDriver(driver: Any): Builder<T> = this
        fun build(): T = error("Not implemented on Web")
    }
}

interface RoomDatabaseConstructor<T>
