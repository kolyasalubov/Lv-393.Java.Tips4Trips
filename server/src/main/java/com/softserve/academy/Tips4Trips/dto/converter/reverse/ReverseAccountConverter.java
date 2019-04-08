package com.softserve.academy.Tips4Trips.dto.converter.reverse;

import com.softserve.academy.Tips4Trips.dto.AccountDTO;
import com.softserve.academy.Tips4Trips.dto.converter.Converter;
import com.softserve.academy.Tips4Trips.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class ReverseAccountConverter  implements Converter<AccountDTO, Account> {


    @Override
    public Account apply(AccountDTO accountDTO) {
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
}
