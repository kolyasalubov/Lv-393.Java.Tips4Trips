package com.softserve.academy.Tips4Trips.dto;

import java.util.List;

public class Page<T> {
    private List<T> content;
    private long number;
    private long totalPages;

    public Page(List<T> content, long number, long totalPages) {
        this.content = content;
        this.number = number;
        this.totalPages = totalPages;
    }

    public List<T> getContent() {
        return content;
    }

    public long getNumber() {
        return number;
    }

    public long getTotalPages() {
        return totalPages;
    }
}
