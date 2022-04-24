package me.czhouyi.demo.application.basic.dto;

import java.util.List;

public class PageResult<T> {
    private List<T> list;
    private long totalCount;
    private long totalPage;
    private long currentPage;

    public PageResult() {
    }

    public static <T> PageResult.PageResultBuilder builder(List<T> list) {
        return (new PageResult.PageResultBuilder<T>()).list(list);
    }

    public PageResult(List<T> list, long totalCount, long totalPage, long currentPage) {
        this.list = list;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
    }

    public List<T> getList() {
        return this.list;
    }

    public long getTotalCount() {
        return this.totalCount;
    }

    public long getTotalPage() {
        return this.totalPage;
    }

    public long getCurrentPage() {
        return this.currentPage;
    }

    public void setList(final List<T> list) {
        this.list = list;
    }

    public void setTotalCount(final long totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(final long totalPage) {
        this.totalPage = totalPage;
    }

    public void setCurrentPage(final long currentPage) {
        this.currentPage = currentPage;
    }

    public static class PageResultBuilder<T> {
        private List<T> list;
        private long totalCount;
        private long totalPage;
        private long currentPage;

        PageResultBuilder() {
        }

        public PageResult.PageResultBuilder<T> list(final List<T> list) {
            this.list = list;
            return this;
        }

        public PageResult.PageResultBuilder<T> totalCount(final long totalCount) {
            this.totalCount = totalCount;
            return this;
        }

        public PageResult.PageResultBuilder<T> totalPage(final long totalPage) {
            this.totalPage = totalPage;
            return this;
        }

        public PageResult.PageResultBuilder<T> currentPage(final long currentPage) {
            this.currentPage = currentPage;
            return this;
        }

        public PageResult<T> build() {
            return new PageResult<>(this.list, this.totalCount, this.totalPage, this.currentPage);
        }

        public String toString() {
            return "PageResult.PageResultBuilder(list=" + this.list + ", totalCount=" + this.totalCount + ", totalPage=" + this.totalPage + ", currentPage=" + this.currentPage + ")";
        }
    }
}
