package com.softserve.academy.Tips4Trips.dto;

import com.softserve.academy.Tips4Trips.dto.details.AccountDetailsDTO;

public class UserAccountDTO {
    UserDTO user;
    AccountDetailsDTO account;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public AccountDetailsDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDetailsDTO account) {
        this.account = account;
    }
}
