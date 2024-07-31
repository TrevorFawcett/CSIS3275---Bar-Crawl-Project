package com.csis3275.service;


import com.csis3275.model.FBUserData;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class ActiveUserStore {

    private List<FBUserData> activeUsers;

    public ActiveUserStore() {
        activeUsers = new ArrayList<>();
    }

    public List<FBUserData> getActiveUsers() {
        return activeUsers;
    }

    public void addActiveUser(FBUserData user) {
        activeUsers.add(user);
    }
}
