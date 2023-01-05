package com.isicod.net.communitiesManagement.services.impl;

import com.isicod.net.communitiesManagement.models.Gerant;
import com.isicod.net.communitiesManagement.repositories.GerantRepository;
import com.isicod.net.communitiesManagement.services.GerantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GerantServiceImpl implements GerantService {
    @Autowired
    GerantRepository gerantRepository;

    @Override
    public List<Gerant> getAllGerant() {
       return gerantRepository.findAll();
    }

}
