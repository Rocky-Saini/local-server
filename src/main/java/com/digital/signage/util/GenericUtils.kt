package com.digital.signage.util

import java.util.function.BinaryOperator
import java.util.stream.Collectors

/**
 * @author -Ravi Kumar created on 1/23/2023 12:35 AM
 * @project - Digital Sign-edge
 */

fun <I, T> List<T>.convertListToMap(
    keyFunction: java.util.function.Function<T, I>
): MutableMap<I, T> =
    this.parallelStream()
        .collect(Collectors.toMap(
            keyFunction,
            java.util.function.Function<T, T> { t -> t }
        ))

fun <I, T> List<T>.convertListToMapWithDuplicates(
    keyFunction: java.util.function.Function<T, I>
): MutableMap<I, T> =
    this.parallelStream()
        .collect(
            Collectors.toMap(
                keyFunction,
                java.util.function.Function<T, T> { t -> t },
                BinaryOperator { t, _ -> t }
            ))

fun <K, V, T> List<T>.convertListToMap(
    keyFunction: java.util.function.Function<T, K>,
    valueFunction: java.util.function.Function<T, V>
): MutableMap<K, V> =
    this.parallelStream()
        .collect(
            Collectors.toMap(
                keyFunction,
                valueFunction
            )
        )

fun <K, V, T> List<T>.convertListToMapWithDuplicates(
    keyFunction: java.util.function.Function<T, K>,
    valueFunction: java.util.function.Function<T, V>
): MutableMap<K, V> =
    this.parallelStream()
        .collect(
            Collectors.toMap(
                keyFunction,
                valueFunction,
                BinaryOperator { t, _ -> t }
            ))