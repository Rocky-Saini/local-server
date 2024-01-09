package com.digital.signage.util;


import com.digital.signage.dto.PaginationDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class PaginationUtility {
    public static <T> PaginationDetail paginationDetail(Page<T> page, Pageable pageRequest) {
        return PaginationDetail.builder().currentPageOffset(pageRequest.getOffset() + 1)
                .totalEntriesPerPage(pageRequest.getPageSize()).totalPages(page.getTotalPages())
                .nextPageOffset(page.getTotalPages() > (pageRequest.getOffset() + 1) ? pageRequest.getOffset() + 1 : null)
                .previousPageOffset(pageRequest.getOffset() > 1 ? pageRequest.getOffset() - 1 : null)
                .firstEntry(page.getNumberOfElements() > 0 ? 1 : 0)
                .lastEntry(Math.max(page.getNumberOfElements(), 0))
                .totalEntries(page.getTotalElements()).build();
    }
}
