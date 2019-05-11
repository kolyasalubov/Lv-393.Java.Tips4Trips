package com.softserve.academy.Tips4Trips.dto.details;

import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.TripInfoDTO;
import com.softserve.academy.Tips4Trips.dto.info.RouteInfoDTO;

import java.util.List;

public class TripDetailsDTO extends TripInfoDTO {

    private AccountDetailsDTO creator;

    private RouteDetailsDTO route;

    private List<AccountInfoDTO> subscribers;

    private Long chatId;

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public AccountDetailsDTO getCreator() {
        return creator;
    }

    public void setCreator(AccountDetailsDTO creator) {
        this.creator = creator;
    }

    public RouteInfoDTO getRoute() {
        return route;
    }

    public void setRoute(RouteDetailsDTO route) {
        this.route = route;
    }

    public List<AccountInfoDTO> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<AccountInfoDTO> subscribers) {
        this.subscribers = subscribers;
    }
}
