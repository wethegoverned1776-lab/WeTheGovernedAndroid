package net.wetheGoverned.model

import kotlinx.serialization.json.Json

val CivicJson = Json {
    ignoreUnknownKeys = true
    encodeDefaults = true
    coerceInputValues = true
    prettyPrint = false
}

object CivicEventKind {
    const val FEDERAL_POLL           = 30_098
    const val STATE_POLL             = 30_099
    const val DISTRICT_POLL          = 30_100
    const val LOCAL_POLL             = 30_102
    const val POLL_VOTE              = 30_101
    const val IMPORTANCE_VOTE        = 30_103
    const val REPRESENTATIVE_SCORE   = 30_200
    const val MANIFESTO              = 30_300
    const val METRIC_REPORT          = 30_400
    const val RESIDENT_PROFILE       = 30_500
    const val COMMUNITY_POST         = 30_600
    const val VERIFICATION_REQUEST   = 30_700
}

typealias CivicScope = PollScope
