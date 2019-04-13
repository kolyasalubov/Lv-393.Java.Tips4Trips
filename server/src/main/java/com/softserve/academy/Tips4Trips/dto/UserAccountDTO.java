package com.softserve.academy.Tips4Trips.dto;

public class UserAccountDTO {
    UserDTO user;
    AccountDTO account;

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public void setAccount(AccountDTO account) {
        this.account = account;
    }
}
