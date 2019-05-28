package com.softserve.academy.Tips4Trips.service;

import com.softserve.academy.Tips4Trips.entity.administration.Account;
import com.softserve.academy.Tips4Trips.entity.chat.Chat;
import com.softserve.academy.Tips4Trips.entity.chat.Message;
import com.softserve.academy.Tips4Trips.entity.enums.Role;
import com.softserve.academy.Tips4Trips.exception.DataNotFoundException;
import com.softserve.academy.Tips4Trips.repository.AccountRepository;
import com.softserve.academy.Tips4Trips.repository.ChatRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    @MockBean
    private ChatRepository chatRepository;

    @MockBean
    private AccountRepository accountRepository;

    private Long existingId = 1L;
    private Long nonExistingId = -1L;
    private Long existingUserId = 2L;
    private Long existingUserId3 = 3L;
    private Long chatId = 1L;
    private Message firstMessage = new Message();
    private Message secondMessage = new Message();
    private Account firstAccount = new Account();
    private Account secondAccount = new Account();

    private Chat chat = null;
    private List<Message> messages;
    @Before
    public void setUp(){
        chat = new Chat();
        chat.setId(existingId);
        firstAccount.setId(1L);
        firstAccount.setRole(Role.USER);
        firstAccount.setRegistrationDate(new Date());
        firstAccount.setFirstName("Volodymyr1");
        firstAccount.setLastName("Paziuk1");
        firstAccount.setEmail("gooasasdasda@gmail.com");
        firstAccount.setSubscribers(null);
        firstAccount.setAbout("about me");
        firstAccount.setPhoneNumber("0678374563");

        firstMessage.setChat(chat);
        firstMessage.setSender(firstAccount);
        firstMessage.setContent("Hello, world1");
        firstMessage.setChat(chat);

        messages = Arrays.asList(firstMessage, secondMessage);
        chat.setMessages(messages);

        Mockito.when(chatRepository.findById(existingId))
                .thenReturn(Optional.of(chat));
        Mockito.when(chatRepository.findById(nonExistingId))
                .thenReturn(Optional.empty());
        Mockito.when(chatRepository.save(chat))
                .thenReturn(chat);
        Mockito.doNothing().when(chatRepository)
                .deleteById(existingId);
        Mockito.when(chatRepository.existsById(existingId))
                .thenReturn(true);
        Mockito.when(chatRepository.existsById(nonExistingId))
                .thenReturn(false);
        Mockito.when(accountRepository.findById(existingUserId))
                .thenReturn(Optional.of(secondAccount));
        //Mockito.when(accountRepository.findById(exi))
        //        .thenReturn(Optional.of(user3));
    }

    @After
    public void tearDown(){
        chat = null;
        firstAccount = null;
        secondAccount = null;
        firstMessage = null;
        secondMessage = null;
        messages = null;
    }

    @Test
    public void whenValidId_thenChatShouldBeFound(){
        Chat found = chatService.getChatById(existingId);
        verifyFindByIdIsCalledOnce();
        assertThat(found.getId()).isEqualTo(chatId);
    }

    @Test(expected = DataNotFoundException.class)
    public void whenNonValidId_thenChatShouldNotBeFound(){
        Chat found = chatService.getChatById(nonExistingId);
        verifyFindByIdIsCalledOnce();
        assertThat(found).isNull();
    }

    private void verifyFindByIdIsCalledOnce() {
        Mockito.verify(chatRepository, VerificationModeFactory.times(1)).findById(Mockito.anyLong());
        Mockito.reset(chatRepository);
    }


}
