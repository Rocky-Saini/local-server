package com.digital.signage.cache

import com.github.benmanes.caffeine.cache.CacheLoader
import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.LoadingCache
import com.google.common.collect.ImmutableMap
import org.springframework.lang.NonNull
import org.springframework.lang.Nullable
import java.util.concurrent.ConcurrentMap
import java.util.concurrent.TimeUnit

/**
 * @author -Ravi Kumar created on 1/19/2023 11:26 AM
 * @project - Digital Sign-edge
 */
abstract class BaseCaffineCacheImpl : Cache {

    protected lateinit var cache: LoadingCache<String, ICachable>

    final fun init() {
        cache = Caffeine.newBuilder()
            .maximumSize(500)
            .recordStats()
            .expireAfterWrite(60, TimeUnit.MINUTES)
            .build(cacheLoader())
    }

    override fun asMap(): ConcurrentMap<String, ICachable> = cache.asMap()

    override fun isCachePresent(@NonNull key: String): Boolean = cache.getIfPresent(key) != null

    @NonNull
    abstract fun cacheLoader(): CacheLoader<String, ICachable>

    @Nullable
    override fun getIfPresent(key: String): ICachable? = cache.getIfPresent(key)

    @Nullable
    override fun get(key: String): ICachable? = cache.get(key)

    abstract fun getKeyByPrefixAndICachable(
        @NonNull prefix: String,
        @NonNull iCachable: ICachable
    ): String

    override fun addToCache(
        @NonNull prefix: String,
        @NonNull iCachable: ICachable
    ) {
        cache.put(
            getKeyByPrefixAndICachable(prefix, iCachable),
            iCachable
        )
    }

    override fun deleteIfPresent(key: String) = if (isCachePresent(key)) {
        cache.invalidate(key)
        true
    } else false

    override fun deleteAllCache(): Map<String, ICachable> {
        val map = ImmutableMap.copyOf(asMap())
        if (map.isNotEmpty())
            cache.invalidateAll()
        return map
    }
}