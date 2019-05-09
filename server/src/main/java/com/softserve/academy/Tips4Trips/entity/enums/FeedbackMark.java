package com.softserve.academy.Tips4Trips.entity.enums;


public enum FeedbackMark {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5);

    private final int index;

    FeedbackMark(int index) {
        this.index = index;
    }

    public int index() {
        return index;
    }

    @Override
    public String toString() {
        return String.valueOf(index);
    }

    public String getStatus() {
        return this.name();
    }
}
