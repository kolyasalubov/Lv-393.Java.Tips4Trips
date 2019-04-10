package com.softserve.academy.Tips4Trips.entity.enums;

import javax.persistence.Entity;
import javax.persistence.Table;


public enum Role {
    ADMIN,
    USER,
    MODERATOR;

    public String getStatus() {
        return this.name();
    }
}
