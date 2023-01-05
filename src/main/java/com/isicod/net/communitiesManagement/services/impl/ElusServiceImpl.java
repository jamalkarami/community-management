package com.isicod.net.communitiesManagement.services.impl;

import com.isicod.net.communitiesManagement.models.Elus;
import com.isicod.net.communitiesManagement.repositories.ElusRepository;
import com.isicod.net.communitiesManagement.services.ElusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElusServiceImpl implements ElusService {

    @Autowired
    private ElusRepository elusRepository;


    @Override
    public List<Elus> getAllElus() {
        return elusRepository.findAll();
    }
}
