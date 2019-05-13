package com.softserve.academy.Tips4Trips.dto.converter;

import com.softserve.academy.Tips4Trips.controller.AccountController;
import com.softserve.academy.Tips4Trips.dto.details.AccountDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.file.ImageDTO;
import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.file.Image;
import com.softserve.academy.Tips4Trips.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountConverter implements Converter<Account, AccountDetailsDTO> {



    @Override
    public AccountDetailsDTO convertToDTO(Account account) {

        AccountDetailsDTO accountDetailsDTO = (AccountDetailsDTO)
                toInfoDTO(new AccountDetailsDTO(), account);
        accountDetailsDTO.setPhoneNumber(account.getPhoneNumber());
        accountDetailsDTO.setEmail(account.getEmail());
        accountDetailsDTO.setRegistrationDate(account.getRegistrationDate());
        accountDetailsDTO.setAbout(account.getAbout());


        accountDetailsDTO.setRole(account.getRole());
        accountDetailsDTO.setNewNotification(account.isNewNotification());
        return accountDetailsDTO;
    }


    @Override
    public Account convertToEntity(AccountDetailsDTO accountDetailsDTO) {
        Account account = new Account();
        account.setId(accountDetailsDTO.getId());
        account.setFirstName(accountDetailsDTO.getFirstName());
        account.setLastName(accountDetailsDTO.getLastName());
        account.setPhoneNumber(accountDetailsDTO.getPhoneNumber());
        account.setEmail(accountDetailsDTO.getEmail());
        account.setRegistrationDate(accountDetailsDTO.getRegistrationDate());
        account.setAbout(accountDetailsDTO.getAbout());


                if (accountDetailsDTO.getImageId() != null) {
            ImageDTO imageDTO = new ImageDTO();
            imageDTO.setId(accountDetailsDTO.getImageId());
            imageDTO.setName(accountDetailsDTO.getImageName());
            imageDTO.setCreator(null);
            imageDTO.setFormat(accountDetailsDTO.getImageFormat());
            imageDTO.setUploadDate(accountDetailsDTO.getImageUploadDate());
           // account.setImage(modelMapper.map(imageDTO, Image.class));

        }




        Image image = new Image();
        image.setId(accountDetailsDTO.getImageId());
        image.setName(accountDetailsDTO.getImageName());
        image.setCreator(null);
        image.setFormat(accountDetailsDTO.getImageFormat());
        image.setUploadDate(accountDetailsDTO.getImageUploadDate());
        //account.setImage(null);



        account.setRole(accountDetailsDTO.getRole());
        return account;
    }

    public AccountInfoDTO convertToInfoDTO(Account account) {
        return toInfoDTO(new AccountInfoDTO(), account);
    }


    public List<AccountInfoDTO> convertToInfoDTO(final List<Account> accounts) {
        List<AccountInfoDTO> dtos = new ArrayList<>();
        if (accounts != null) {
            dtos = accounts.stream().map(this::convertToInfoDTO).collect(Collectors.toList());
        }
        return dtos;
    }


    private AccountInfoDTO toInfoDTO(AccountInfoDTO accountInfoDTO, Account account) {
        accountInfoDTO.setId(account.getId());
        accountInfoDTO.setFirstName(account.getFirstName());
        accountInfoDTO.setLastName(account.getLastName());
        if (account.getImage() != null) {
            accountInfoDTO.setImageId(account.getImage().getId());
            accountInfoDTO.setImageFormat(account.getImage().getFormat());
            accountInfoDTO.setImageName(account.getImage().getName());
            accountInfoDTO.setImageUploadDate(account.getImage().getUploadDate());
            accountInfoDTO.setImageCreatorId(account.getImage().getCreator().getId());
        }

        accountInfoDTO.setSelf(ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder
                        .methodOn(AccountController.class)
                        .getById(account.getId()))
                .withSelfRel().getHref());
        return accountInfoDTO;
    }

}
