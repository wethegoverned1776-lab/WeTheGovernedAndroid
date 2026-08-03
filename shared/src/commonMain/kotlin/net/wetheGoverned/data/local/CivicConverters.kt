package net.wetheGoverned.data.local

import androidx.room3.ColumnTypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.wetheGoverned.model.*

class CivicConverters {
    private val json = Json { ignoreUnknownKeys = true }

    @ColumnTypeConverter
    fun fromPollOptions(value: List<PollOption>): String = json.encodeToString(value)

    @ColumnTypeConverter
    fun toPollOptions(value: String): List<PollOption> = json.decodeFromString(value)

    @ColumnTypeConverter
    fun fromPollPosts(value: List<PollPost>): String = json.encodeToString(value)

    @ColumnTypeConverter
    fun toPollPosts(value: String): List<PollPost> = json.decodeFromString(value)

    @ColumnTypeConverter
    fun fromDistrictBreakdown(value: Map<String, Map<String, Int>>?): String? = 
        value?.let { json.encodeToString(it) }

    @ColumnTypeConverter
    fun toDistrictBreakdown(value: String?): Map<String, Map<String, Int>>? = 
        value?.let { json.decodeFromString(it) }

    @ColumnTypeConverter
    fun fromPollScope(value: PollScope): String = value.name

    @ColumnTypeConverter
    fun toPollScope(value: String): PollScope = try { PollScope.valueOf(value) } catch (e: Exception) { PollScope.DISTRICT }

    @ColumnTypeConverter
    fun fromPollStatus(value: PollStatus): String = value.name

    @ColumnTypeConverter
    fun toPollStatus(value: String): PollStatus = try { PollStatus.valueOf(value) } catch (e: Exception) { PollStatus.ACTIVE }

    @ColumnTypeConverter
    fun fromConflictStatus(value: ConflictStatus): String = value.name

    @ColumnTypeConverter
    fun toConflictStatus(value: String): ConflictStatus = try { ConflictStatus.valueOf(value) } catch (e: Exception) { ConflictStatus.NONE }

    @ColumnTypeConverter
    fun fromVerificationTier(value: VerificationTier): String = value.name

    @ColumnTypeConverter
    fun toVerificationTier(value: String): VerificationTier = try { VerificationTier.valueOf(value) } catch (e: Exception) { VerificationTier.OBSERVER }

    @ColumnTypeConverter
    fun fromDistrictLevel(value: DistrictLevel): String = value.name

    @ColumnTypeConverter
    fun toDistrictLevel(value: String): DistrictLevel = try { DistrictLevel.valueOf(value) } catch (e: Exception) { DistrictLevel.FEDERAL_HOUSE }

    @ColumnTypeConverter
    fun fromCommunityPostKind(value: CommunityPostKind): String = value.name

    @ColumnTypeConverter
    fun toCommunityPostKind(value: String): CommunityPostKind = try { CommunityPostKind.valueOf(value) } catch (e: Exception) { CommunityPostKind.GENERAL }

    @ColumnTypeConverter
    fun fromVerificationRequestStatus(value: VerificationRequestStatus): String = value.name

    @ColumnTypeConverter
    fun toVerificationRequestStatus(value: String): VerificationRequestStatus = try { VerificationRequestStatus.valueOf(value) } catch (e: Exception) { VerificationRequestStatus.PENDING }

    @ColumnTypeConverter
    fun fromMetricSource(value: MetricSource): String = value.name

    @ColumnTypeConverter
    fun toMetricSource(value: String): MetricSource = try { MetricSource.valueOf(value) } catch (e: Exception) { MetricSource.OFFICIAL }
}
