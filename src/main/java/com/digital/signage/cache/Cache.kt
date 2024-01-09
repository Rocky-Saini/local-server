package com.digital.signage.cache

import org.jetbrains.annotations.NotNull
import org.springframework.lang.NonNull
import org.springframework.lang.Nullable
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentMap

/**
 * @author -Ravi Kumar created on 1/19/2023 11:26 AM
 * @project - Digital Sign-edge
 */
@Service
interface Cache {

    fun isCachePresent(@NonNull key: String): Boolean

    @Nullable
    fun getIfPresent(key: String): ICachable?

    @Nullable
    fun get(@NotNull key: String): ICachable?

    fun addListItemsToCache(@NotNull prefix: String, @NotNull list: List<ICachable>)

    fun addToCache(@NotNull prefix: String, @NotNull iCachable: ICachable)

    fun asMap(): ConcurrentMap<String, ICachable>

    fun deleteIfPresent(key: String): Boolean

    fun deleteAllCache(): Map<String, ICachable>
}