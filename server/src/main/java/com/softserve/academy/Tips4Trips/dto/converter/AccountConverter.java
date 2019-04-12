package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.AccountDTO;
import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter implements Converter<Account, AccountDTO> {


    @Override
    public AccountDTO convertToDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setFirstName(account.getFirstName());
        accountDTO.setLastName(account.getLastName());
        accountDTO.setPhoneNumber(account.getPhoneNumber());
        accountDTO.setEmail(account.getEmail());
        accountDTO.setRegistrationDate(account.getRegistrationDate());
        accountDTO.setAbout(account.getAbout());
        accountDTO.setRole(account.getRole());
        return accountDTO;
    }

    @Override
    public Account convertToEntity(AccountDTO accountDTO) {
        Account account = new Account();
        account.setFirstName(accountDTO.getFirstName());
        account.setLastName(accountDTO.getLastName());
        account.setPhoneNumber(accountDTO.getPhoneNumber());
        account.setEmail(accountDTO.getEmail());
        account.setRegistrationDate(accountDTO.getRegistrationDate());
        account.setAbout(accountDTO.getAbout());
        account.setRole(accountDTO.getRole());
        return account;
    }

    public AccountInfoDTO convertToInfoDTO(Account account) {
        AccountInfoDTO accountInfoDTO = new AccountInfoDTO();
        accountInfoDTO.setId(account.getId());
        return accountInfoDTO;
    }
}
