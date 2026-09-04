package com.mahmoudcasio.pornblocker

import java.net.URI

object DomainMatcher {
    private val blockedDomains = setOf(
        "pornhub.com","xvideos.com","xnxx.com","youporn.com","redtube.com",
        "tube8.com","spankbang.com","xhamster.com","beeg.com","brazzers.com",
        "bangbros.com","chaturbate.com","myfreecams.com","stripchat.com",
        "camsoda.com","manyvids.com"
    )

    fun hostFrom(url: String): String? = try {
        var candidate = url.trim()
        if (!candidate.contains("://")) candidate = "https://$candidate"
        URI(candidate).host?.lowercase()?.trimEnd('.')
    } catch (_: Exception) { null }

    fun isBlocked(url: String): Boolean {
        val host = hostFrom(url) ?: return false
        return blockedDomains.any { domain ->
            host == domain || host.endsWith(".$domain")
        }
    }
}
