package com.digital.signage.util;

import com.digital.signage.models.Pagination;
import com.google.common.collect.ImmutableList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author -Ravi Kumar created on 1/25/2023 2:25 AM
 * @project - Digital Sign-edge
 */
public class PaginationUtils {

    private PaginationUtils() {
        // Throw an exception if this ever is called
        throw new AssertionError("Instantiating utility class not allowed.");
    }

  /*
        for pagination we are provide in pageRequest currentPage and numPerPage.

     */

    public static Pagination getPagination(Page page, Pageable pageRequest) {
        Pagination pagination = new Pagination();
        Integer offset = Math.toIntExact(pageRequest.getOffset());
        pagination.setCurrentPage(offset + 1);
        pagination.setNumPerPage(pageRequest.getPageSize());
        pagination.setPageCount(page.getTotalPages());
        pagination.setNextPage(page.getTotalPages() > (offset + 1) ? offset + 1 : null);
        pagination.setPreviousPage(offset > 1 ? offset - 1 : null);
        pagination.setFirstItemNumber(page.getNumberOfElements() > 0 ? 1 : 0);
        pagination.setLastItemNumber(Math.max(page.getNumberOfElements(), 0));
        pagination.setTotalItemCount(page.getTotalElements());
        return pagination;
    }

    public static Pagination getPagination(Integer totalCount, Integer currentPage,
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
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(currentPage);
        pagination.setNumPerPage(numPerPage);
        pagination.setPageCount(totalPage);
        pagination.setNextPage(totalPage > currentPage ? currentPage + 1 : null);
        pagination.setPreviousPage(currentPage > 1 ? currentPage - 1 : null);
        pagination.setFirstItemNumber(((currentPage - 1) * numPerPage) + 1);
        pagination.setLastItemNumber(
                pagination.getNextPage() == null ? totalCount : ((currentPage - 1) * numPerPage) + count);
        pagination.setTotalItemCount((Long.valueOf(totalCount)));
        return pagination;
    }

    public static <T> List<T> getPaginationOnList(final List<T> sourceList, final Integer pageNumber,
                                                  final Integer itemPerPage) {
        if (ObjectUtils.isEmpty(sourceList)) {
            return new ArrayList<>(0);
        }
        if (pageNumber == null || itemPerPage == null) {
            throw new IllegalArgumentException("page Number and itemPerPage should not be null");
        }
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("invalid page number: " + pageNumber);
        }
        if (itemPerPage <= 0) {
            throw new IllegalArgumentException("invalid page size: " + itemPerPage);
        }
        int fromIndex = (pageNumber - 1) * itemPerPage;
        int totalCount = sourceList.size();
        if (totalCount < fromIndex) {
            return new ArrayList<>(0);
        }
        // toIndex exclusive
        return ImmutableList.copyOf(
                sourceList.subList(fromIndex, Math.min(fromIndex + itemPerPage, sourceList.size())));
    }
}
