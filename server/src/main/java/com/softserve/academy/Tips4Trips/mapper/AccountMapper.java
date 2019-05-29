package com.softserve.academy.Tips4Trips.mapper;

import com.softserve.academy.Tips4Trips.dto.details.AccountDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {
    private ModelMapper modelMapper;
@Autowired
    public AccountMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public AccountDetailsDTO toDetailsDTO(Account account){
        return modelMapper.map(account,AccountDetailsDTO.class);
    }
    public AccountInfoDTO toInfoDTO(Account account){
        return modelMapper.map(account,AccountInfoDTO.class);
    }
}
