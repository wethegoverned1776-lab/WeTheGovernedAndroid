package net.wetheGoverned.model

fun CivicPoll.calculateWeightedResults(): Map<String, Float> {
    if (totalVotes == 0) return emptyMap()
    val results = mutableMapOf<String, Float>()
    val opts = options
    val count = opts.size
    for (i in 0 until count) {
        val option = opts.get(i)
        results.put(option.id, option.voteCount.toFloat() / totalVotes)
    }
    return results
}
