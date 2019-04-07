package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.dto.AccountDTO;
import com.softserve.academy.Tips4Trips.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter implements Converter<Account, AccountDTO> {

    @Override
    public AccountDTO apply(Account account) {
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
}
