package com.softserve.academy.Tips4Trips.dto;

import java.util.List;

public class Page<T> {
    private List<T> list;
    private long page;
    private long total;

    public Page(List<T> list, long page, long total) {
        this.list = list;
        this.page = page;
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public long getPage() {
        return page;
    }

    public long getTotal() {
        return total;
    }
}
