package com.softserve.academy.Tips4Trips.controller;


import com.softserve.academy.Tips4Trips.dto.AccountDTO;
import com.softserve.academy.Tips4Trips.dto.AccountRoleDTO;
import com.softserve.academy.Tips4Trips.dto.News.AccountFeedDTO;
import com.softserve.academy.Tips4Trips.dto.News.AccountFollowingDTO;
import com.softserve.academy.Tips4Trips.dto.converter.AccountConverter;
import com.softserve.academy.Tips4Trips.dto.details.AccountDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.details.PostDetailsDTO;
import com.softserve.academy.Tips4Trips.dto.file.ImageDTO;
import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.PostInfoDTO;
import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.blog.Post;
import com.softserve.academy.Tips4Trips.entity.file.Image;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.exception.FileIOException;
import com.softserve.academy.Tips4Trips.service.AccountService;
import com.softserve.academy.Tips4Trips.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/accounts")
public class AccountController {

    private static final Logger logger = Logger.getLogger(AccountController.class);

    private AccountService accountService;
    private AccountConverter accountConverter;
    private UserService userService;
    private ModelMapper modelMapper;


    @Autowired
    public AccountController(AccountService accountService, AccountConverter accountConverter, UserService userService, ModelMapper modelMapper) {
        this.accountService = accountService;
        this.accountConverter = accountConverter;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    @GetMapping("/count")
    public ResponseEntity<Long> getCount() {
        logger.info("get post by id method executing: ");
        return new ResponseEntity<>(accountService.getCount(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<AccountInfoDTO>> getAll() {
        logger.info("get all method executing: ");
        return new ResponseEntity<>(accountConverter.convertToInfoDTO(accountService
                .findAll()), HttpStatus.OK);
    }

    @RequestMapping(value = "/page/{page}", method = RequestMethod.GET)
    public Page<AccountRoleDTO> listArticlesPageByPage(@PathVariable("page") int page) {
        PageRequest pageable = PageRequest.of(page - 1, 100);
        Page<Account> articlePage = accountService.getPaginatedArticles(pageable);
        Page<AccountRoleDTO> postDetailsDTOS = articlePage.map(account -> modelMapper.map(account, AccountRoleDTO.class));

        return postDetailsDTOS;

    }


    @GetMapping("/{id}")
    public ResponseEntity<AccountDetailsDTO> getById(@PathVariable Long id) {
        logger.info("get by id method executing: ");
        return new ResponseEntity<>(accountConverter
                .convertToDTO(accountService.findById(id)), HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<AccountDetailsDTO> createAccount(@RequestBody AccountDetailsDTO accountDTO) {
        logger.info("create account method executing: ");
        Account account = accountService.createAccount(accountConverter.convertToEntity(accountDTO));
        return new ResponseEntity<>(accountConverter.convertToDTO(account), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<AccountDetailsDTO> updateAccount(@RequestBody AccountDetailsDTO accountDTO) {
        logger.info("update account method executing: ");
        Account account = accountService.update(accountConverter.convertToEntity(accountDTO));
        return new ResponseEntity<>(accountConverter.convertToDTO(account), HttpStatus.ACCEPTED);

    }

    @PutMapping("/update/role")
    public ResponseEntity<AccountDetailsDTO> updateAccountRole(@RequestBody AccountDetailsDTO accountDTO) {
        logger.info("update account method executing: ");
        Account account = accountService.findById(accountDTO.getId());
        account.setRole(accountDTO.getRole());
        account = accountService.update(account);
        return new ResponseEntity<>(accountConverter.convertToDTO(account),
                HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        logger.info("delete by id method executing: ");
        accountService.deleteById(id);
    }

    @GetMapping("/me")
    public AccountDetailsDTO getCurrentUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();
        AccountDetailsDTO accountDetailsDTO = (accountConverter.convertToDTO(
                accountService.findByUser(userService.findByLogin(userDetails.getUsername()))));
        return accountDetailsDTO;
    }


    @PutMapping("/{accountId}/subscribe/{profileId}")
//    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<AccountInfoDTO> subscribe(
            @PathVariable(value = "accountId") @NotNull @Positive Long accountId,
            @PathVariable(value = "profileId") @NotNull @Positive Long profileId) {

        //todo add validation
        return new ResponseEntity<>(accountConverter.convertToInfoDTO(accountService.subscribe(accountId, profileId)), HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}/unsubscribe/{profileId}")
    //    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity unSubscribe(
            @PathVariable(value = "accountId") @NotNull @Positive Long accountId,
            @PathVariable(value = "profileId") @NotNull @Positive Long profileId) {
        //todo add validation
        accountService.unSubscribe(accountId, profileId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/news")
    public ResponseEntity<AccountFeedDTO> getAccountFeedById(@PathVariable Long id) {
        logger.info("get by id method executing: ");
        Account account = accountService.findById(id);

        AccountFeedDTO accountFeedDTO = modelMapper.map(account, AccountFeedDTO.class);
        List<AccountFollowingDTO> accountFollowingDTOS = account.getSubscribers()
                .stream()
                .map(source -> modelMapper.map(source, AccountFollowingDTO.class))
                .collect(Collectors.toList());
        accountFeedDTO.setFollowingAccounts(accountFollowingDTOS);

        return new ResponseEntity<>(accountFeedDTO, HttpStatus.OK);
    }


    @PostMapping("/{id}/image")
    public ResponseEntity<AccountDetailsDTO> addImage(@PathVariable Long id,
            @RequestParam("file") MultipartFile file) throws FileIOException {
        Account updatedAccount = accountService.createImageForAccount(file, id);
        return new ResponseEntity<>(accountConverter
                .convertToDTO(updatedAccount), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/image")
    public RedirectView redirectToImageGet(@PathVariable Long id) {
        Image image = accountService.findById(id).getImage();
        Long imageId = image == null ? -1 : image.getId();
        return new RedirectView("/images/" + imageId);
    }

    @DeleteMapping("/{id}/image")
    public void deleteImageById(@PathVariable Long id) throws FileIOException,
            DataNotFoundException {
        logger.info("delete image account by id method executing: ");
        accountService.deleteAccountImage(id);
    }

    @PutMapping("/{id}/image")
    public ResponseEntity<AccountDetailsDTO> updateImageById(
            @PathVariable Long id, @RequestParam("file") MultipartFile file)
            throws FileIOException, DataNotFoundException {

        logger.info("update image account by id method executing: ");
        Account updatedAccount = accountService.updateAccountImage(id, file);
        return new ResponseEntity<>(accountConverter
                .convertToDTO(updatedAccount), HttpStatus.ACCEPTED);
    }
}
