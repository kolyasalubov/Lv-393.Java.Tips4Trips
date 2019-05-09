package com.softserve.academy.Tips4Trips.dto.News;

import com.softserve.academy.Tips4Trips.dto.info.AccountInfoDTO;

import java.util.List;

public class AccountFeedDTO extends AccountInfoDTO {
    List<AccountFollowingDTO> followingAccounts;

    public List<AccountFollowingDTO> getFollowingAccounts() {
        return followingAccounts;
    }

    public void setFollowingAccounts(List<AccountFollowingDTO> followingAccounts) {
        this.followingAccounts = followingAccounts;
    }
}
