package com.digital.signage.dto;



import com.digital.signage.util.ApplicationConstants;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class PaginationDetail {
    @JsonProperty("numPerPage")
    private Integer totalEntriesPerPage;
    @JsonProperty("pageCount")
    private Integer totalPages;
    @JsonProperty("currentPage")
    private Long currentPageOffset;
    @JsonProperty("previousPage")
    private Long previousPageOffset;
    @JsonProperty("nextPage")
    private Long nextPageOffset;
    @JsonProperty("firstItemNumber")
    private Integer firstEntry;
    @JsonProperty("lastItemNumber")
    private Integer lastEntry;
    @JsonProperty("totalItemCount")
    private Long totalEntries;

    public static PaginationDetail getPagination(Integer totalCount, Integer currentPage,
                                           Integer numPerPage, Integer count) {
        currentPage = (currentPage != null && currentPage > 0) ? currentPage
                : ApplicationConstants.DEFAULT_PAGE_NO_FOR_REPORTS_PAGINATION;
        numPerPage = (numPerPage != null &&
                numPerPage <= ApplicationConstants.MAX_ITEMS_PER_PAGE_FOR_REPORTS_PAGINATION)
                ? numPerPage : ApplicationConstants.MAX_ITEMS_PER_PAGE_FOR_REPORTS_PAGINATION;
        int totalPage = (numPerPage > 0) ? (totalCount / numPerPage) : 0;
        if (numPerPage > 0 && totalCount % numPerPage != 0) {
            totalPage++;
        }
        Long nextPageOffset = totalPage > currentPage ? Long.valueOf(currentPage + 1) : null;
        return PaginationDetail.builder()
                .currentPageOffset(Long.valueOf(currentPage))
                .totalEntriesPerPage(numPerPage)
                .totalPages(totalPage)
                .nextPageOffset(nextPageOffset)
                .previousPageOffset(currentPage > 1 ? Long.valueOf(currentPage - 1) : null)
                .firstEntry(((currentPage - 1) * numPerPage) + 1)
                .lastEntry(nextPageOffset == null ? totalCount : ((currentPage - 1) * numPerPage) + count)
                .totalEntries((Long.valueOf(totalCount))).build();
    }
}
