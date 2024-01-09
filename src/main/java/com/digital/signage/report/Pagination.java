package com.digital.signage.report;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class Pagination {
    public static final String JSON_KEY_NUM_PER_PAGE = "numPerPage";
    public static final String JSON_KEY_CURRENT_PAGE = "currentPage";
    public static final String JSON_KEY_PAGE_COUNT = "pageCount";
    public static final String JSON_KEY_PREVIOUS_PAGE = "previousPage";
    public static final String JSON_KEY_NEXT_PAGE = "nextPage";
    public static final String JSON_KEY_FIRST_ITEM_NUMBER = "firstItemNumber";
    public static final String JSON_KEY_LAST_ITEM_NUMBER = "lastItemNumber";
    public static final String JSON_KEY_TOTAL_ITEM_COUNT = "totalItemCount";
    @JsonProperty(JSON_KEY_NUM_PER_PAGE)
    private Integer numPerPage;
    @JsonProperty(JSON_KEY_CURRENT_PAGE)
    private Integer currentPage;
    @JsonProperty(JSON_KEY_PAGE_COUNT)
    private Integer pageCount;
    @JsonProperty(JSON_KEY_PREVIOUS_PAGE)
    private Integer previousPage;
    @JsonProperty(JSON_KEY_NEXT_PAGE)
    private Integer nextPage;
    @JsonProperty(JSON_KEY_FIRST_ITEM_NUMBER)
    private Integer firstItemNumber;
    @JsonProperty(JSON_KEY_LAST_ITEM_NUMBER)
    private Integer lastItemNumber;
    @JsonProperty(JSON_KEY_TOTAL_ITEM_COUNT)
    private Long totalItemCount;

    public Pagination() {
    }

    public Pagination(
            Integer numPerPage,
            Integer currentPage,
            Integer pageCount,
            Integer previousPage,
            Integer nextPage,
            Integer firstItemNumber,
            Integer lastItemNumber,
            Long totalItemCount
    ) {
        this.numPerPage = numPerPage;
        this.currentPage = currentPage;
        this.pageCount = pageCount;
        this.previousPage = previousPage;
        this.nextPage = nextPage;
        this.firstItemNumber = firstItemNumber;
        this.lastItemNumber = lastItemNumber;
        this.totalItemCount = totalItemCount;
    }

    public Integer getNumPerPage() {
        return numPerPage;
    }

    public void setNumPerPage(Integer numPerPage) {
        this.numPerPage = numPerPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(Integer previousPage) {
        this.previousPage = previousPage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    public Integer getFirstItemNumber() {
        return firstItemNumber;
    }

    public void setFirstItemNumber(Integer firstItemNumber) {
        this.firstItemNumber = firstItemNumber;
    }

    public Integer getLastItemNumber() {
        return lastItemNumber;
    }

    public void setLastItemNumber(Integer lastItemNumber) {
        this.lastItemNumber = lastItemNumber;
    }

    public Long getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(Long totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public static final class Builder {
        private Integer numPerPage;
        private Integer currentPage;
        private Integer pageCount;
        private Integer previousPage;
        private Integer nextPage;
        private Integer firstItemNumber;
        private Integer lastItemNumber;
        private Long totalItemCount;

        private Builder() {
        }

        public static Builder aPagination() {
            return new Builder();
        }

        public Builder numPerPage(Integer numPerPage) {
            this.numPerPage = numPerPage;
            return this;
        }

        public Builder currentPage(Integer currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public Builder pageCount(Integer pageCount) {
            this.pageCount = pageCount;
            return this;
        }

        public Builder previousPage(Integer previousPage) {
            this.previousPage = previousPage;
            return this;
        }

        public Builder nextPage(Integer nextPage) {
            this.nextPage = nextPage;
            return this;
        }

        public Builder firstItemNumber(Integer firstItemNumber) {
            this.firstItemNumber = firstItemNumber;
            return this;
        }

        public Builder lastItemNumber(Integer lastItemNumber) {
            this.lastItemNumber = lastItemNumber;
            return this;
        }

        public Builder totalItemCount(Long totalItemCount) {
            this.totalItemCount = totalItemCount;
            return this;
        }

        public Pagination build() {
            Pagination pagination = new Pagination();
            pagination.setNumPerPage(numPerPage);
            pagination.setCurrentPage(currentPage);
            pagination.setPageCount(pageCount);
            pagination.setPreviousPage(previousPage);
            pagination.setNextPage(nextPage);
            pagination.setFirstItemNumber(firstItemNumber);
            pagination.setLastItemNumber(lastItemNumber);
            pagination.setTotalItemCount(totalItemCount);
            return pagination;
        }
    }

    public static Pagination create(Page<?> page, Pageable pageRequest) {
        Pagination pagination = new Pagination();
        pagination.setCurrentPage(Long.valueOf(pageRequest.getOffset() + 1).intValue());
        pagination.setNumPerPage(pageRequest.getPageSize());
        pagination.setPageCount(page.getTotalPages());
        pagination.setNextPage(
                page.getTotalPages() > (pageRequest.getOffset() + 1) ? Long.valueOf(pageRequest.getOffset() + 1).intValue() : null);
        pagination.setPreviousPage(pageRequest.getOffset() > 1 ? Long.valueOf(pageRequest.getOffset() - 1).intValue() : null);
        pagination.setFirstItemNumber(page.getNumberOfElements() > 0 ? 1 : 0);
        pagination.setLastItemNumber(Math.max(page.getNumberOfElements(), 0));
        pagination.setTotalItemCount(page.getTotalElements());
        return pagination;
    }
}
