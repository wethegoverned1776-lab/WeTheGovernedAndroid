package net.wetheGoverned.data.local

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import net.wetheGoverned.model.*
import net.wetheGoverned.core.CivicJson

class CivicConverters {
    @TypeConverter
    fun fromPollOptions(value: List<PollOption>): String = CivicJson.instance.encodeToString(value)

    @TypeConverter
    fun toPollOptions(value: String): List<PollOption> = CivicJson.instance.decodeFromString(value)

    @TypeConverter
    fun fromPollPosts(value: List<PollPost>): String = CivicJson.instance.encodeToString(value)

    @TypeConverter
    fun toPollPosts(value: String): List<PollPost> = CivicJson.instance.decodeFromString(value)

    @TypeConverter
    fun fromDistrictBreakdown(value: Map<String, Int>?): String? = 
        value?.let { CivicJson.instance.encodeToString(it) }

    @TypeConverter
    fun toDistrictBreakdown(value: String?): Map<String, Int>? =
        value?.let { CivicJson.instance.decodeFromString(it) }

    @TypeConverter
    fun fromPollScope(value: PollScope): String = value.name

    @TypeConverter
    fun toPollScope(value: String): PollScope = try { PollScope.valueOf(value) } catch (e: Exception) { PollScope.DISTRICT }

    @TypeConverter
    fun fromPollStatus(value: PollStatus): String = value.name

    @TypeConverter
    fun toPollStatus(value: String): PollStatus = try { PollStatus.valueOf(value) } catch (e: Exception) { PollStatus.ACTIVE }

    @TypeConverter
    fun fromConflictStatus(value: ConflictStatus): String = value.name

    @TypeConverter
    fun toConflictStatus(value: String): ConflictStatus = try { ConflictStatus.valueOf(value) } catch (e: Exception) { ConflictStatus.NONE }

    @TypeConverter
    fun fromVerificationTier(value: VerificationTier): String = value.name

    @TypeConverter
    fun toVerificationTier(value: String): VerificationTier = try { VerificationTier.valueOf(value) } catch (e: Exception) { VerificationTier.OBSERVER }

    @TypeConverter
    fun fromDistrictLevel(value: DistrictLevel): String = value.name

    @TypeConverter
    fun toDistrictLevel(value: String): DistrictLevel = try { DistrictLevel.valueOf(value) } catch (e: Exception) { DistrictLevel.FEDERAL_HOUSE }

    @TypeConverter
    fun fromCommunityPostKind(value: CommunityPostKind): String = value.name

    @TypeConverter
    fun toCommunityPostKind(value: String): CommunityPostKind = try { CommunityPostKind.valueOf(value) } catch (e: Exception) { CommunityPostKind.GENERAL }

    @TypeConverter
    fun fromVerificationRequestStatus(value: VerificationRequestStatus): String = value.name

    @TypeConverter
    fun toVerificationRequestStatus(value: String): VerificationRequestStatus = try { VerificationRequestStatus.valueOf(value) } catch (e: Exception) { VerificationRequestStatus.PENDING }

    @TypeConverter
    fun fromMetricSource(value: MetricSource): String = value.name

    @TypeConverter
    fun toMetricSource(value: String): MetricSource = try { MetricSource.valueOf(value) } catch (e: Exception) { MetricSource.OFFICIAL }
}
