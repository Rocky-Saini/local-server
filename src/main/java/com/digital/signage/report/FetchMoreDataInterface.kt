package com.digital.signage.report

@FunctionalInterface
interface FetchMoreDataInterface<T> {
    fun fetchMoreData(pageNumber: Int, maxRecordsToFetchInOneIteration: Int): List<T>?

    fun resetToStart()
}