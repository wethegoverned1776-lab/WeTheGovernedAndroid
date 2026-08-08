package androidx.room

import kotlin.coroutines.CoroutineContext

abstract class RoomDatabase {
    open class Builder<T : RoomDatabase> {
        fun fallbackToDestructiveMigration(enabled: Boolean = true): Builder<T> = this
        fun setQueryCoroutineContext(context: CoroutineContext): Builder<T> = this
        fun setDriver(driver: Any): Builder<T> = this
        fun build(): T = error("Room not supported on Web")
    }
}

object Room {
    fun <T : RoomDatabase> databaseBuilder(context: Any?, name: String): RoomDatabase.Builder<T> = RoomDatabase.Builder()
    fun <T : RoomDatabase> inMemoryDatabaseBuilder(context: Any?): RoomDatabase.Builder<T> = RoomDatabase.Builder()
}

interface RoomDatabaseConstructor<T> {
    fun initialize(): T
}

annotation class Database(
    val entities: Array<kotlin.reflect.KClass<*>>,
    val version: Int,
    val exportSchema: Boolean = true
)

annotation class Dao
annotation class Query(val value: String)
annotation class Insert(val onConflict: Int = 0)
annotation class Update
annotation class Delete
annotation class Upsert
annotation class Transaction
annotation class TypeConverters(vararg val value: kotlin.reflect.KClass<*>)
annotation class TypeConverter
annotation class Entity(val tableName: String = "")
annotation class PrimaryKey(val autoGenerate: Boolean = false)
annotation class ConstructedBy(val value: kotlin.reflect.KClass<*>)
annotation class ColumnInfo(val name: String = "")

object OnConflictStrategy {
    const val REPLACE = 1
}
