package com.marmotshop.inventory_manager.application.shared;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class ResponsePage<T> {
    private long totalRecords;
    private long page;
    private long limit;
    private Collection<T> records;

    public ResponsePage(final long totalRecords, final long page, final long limit, final List<T> records) {
        this.totalRecords = totalRecords;
        this.page = page;
        this.limit = limit;
        this.records = records;
    }

    public ResponsePage(final long totalRecords, final long page, final long limit, final Set<T> records) {
        this.totalRecords = totalRecords;
        this.page = page;
        this.limit = limit;
        this.records = records;
    }

    public ResponsePage() {}

    public long getTotalRecords() {
        return totalRecords == 0L ? records.size() : totalRecords;
    }

    public ResponsePage<T> setTotalRecords(final long totalRecords) {
        this.totalRecords = totalRecords;
        return this;
    }

    public long getPage() {
        return page;
    }

    public ResponsePage<T> setPage(final long page) {
        this.page = page;
        return this;
    }

    public long getLimit() {
        return limit;
    }

    public ResponsePage<T> setLimit(final long limit) {
        this.limit = limit;
        return this;
    }

    public Collection<T> getRecords() {
        return records;
    }

    public ResponsePage<T> setRecords(final Collection<T> records) {
        this.records = records == null ? Collections.emptyList() : records;
        return this;
    }
}
