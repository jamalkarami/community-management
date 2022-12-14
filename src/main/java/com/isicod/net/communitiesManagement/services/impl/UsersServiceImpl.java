package com.isicod.net.communitiesManagement.services.impl;

import com.isicod.net.communitiesManagement.dto.UsersDtoAuthentication;
import com.isicod.net.communitiesManagement.exceptions.FailedAuthentication;
import com.isicod.net.communitiesManagement.models.Users;
import com.isicod.net.communitiesManagement.repositories.UsersRepository;
import com.isicod.net.communitiesManagement.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users authenticateUser(UsersDtoAuthentication userDto) {

        Users user= usersRepository.findByTelephoneAndPassword(userDto.getTelephone(),userDto.getPassword());
        if(user!=null){
            return user;
        }
        else{
            throw new FailedAuthentication("Combinaison telephone et mot de passe est incorrect");
        }
    }
}
