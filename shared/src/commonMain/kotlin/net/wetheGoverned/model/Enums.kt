package net.wetheGoverned.model

import kotlinx.serialization.Serializable

@Serializable
enum class VerificationTier {
    OBSERVER, VERIFIED
}

@Serializable
enum class ConflictStatus {
    NONE, FLAGGED, DISPUTED, RESOLVED
}

@Serializable
enum class PollStatus {
    ACTIVE, CLOSED, ARCHIVED
}

@Serializable
enum class PollScope {
    DASHBOARD, FEDERAL, STATE, DISTRICT, LOCAL, ALL_POLLS, REPRESENTATIVES, RESULTS
}

@Serializable
enum class DistrictLevel {
    FEDERAL_HOUSE, FEDERAL_SENATE, STATE_SENATE, STATE_HOUSE, COUNTY, CITY, SCHOOL_BOARD, SPECIAL
}

@Serializable
enum class MetricSource {
    OFFICIAL, RESIDENT_REPORTED
}

@Serializable
enum class CommunityPostKind {
    MARKETPLACE, WORKSHOP, CLASS, JOB, GENERAL
}

@Serializable
enum class VerificationRequestStatus {
    PENDING, VERIFIED, CLOSED
}
