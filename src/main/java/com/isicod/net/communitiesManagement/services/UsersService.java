package com.isicod.net.communitiesManagement.services;

import com.isicod.net.communitiesManagement.dto.UsersDtoAuthentication;
import com.isicod.net.communitiesManagement.models.Users;

public interface UsersService {

    public Users authenticateUser(UsersDtoAuthentication user);
}
