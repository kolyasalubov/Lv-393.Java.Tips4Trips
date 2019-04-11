package com.softserve.academy.Tips4Trips.dto;

public class UserAccountDTO {
    private UserDTO userDto;
    private AccountDTO accountDTO;

    public UserDTO getUserDto() {
        return userDto;
    }

    public void setUserDto(UserDTO userDto) {
        this.userDto = userDto;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    public void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }
}
