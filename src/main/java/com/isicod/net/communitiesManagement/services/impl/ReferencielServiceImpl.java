package com.isicod.net.communitiesManagement.services.impl;

import com.isicod.net.communitiesManagement.models.SousTypeReclamation;
import com.isicod.net.communitiesManagement.models.TypeReclamation;
import com.isicod.net.communitiesManagement.repositories.SousTypeReclamationRepository;
import com.isicod.net.communitiesManagement.repositories.TypeReclamationRepository;
import com.isicod.net.communitiesManagement.services.ReferencielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferencielServiceImpl implements ReferencielService {

    @Autowired
    private TypeReclamationRepository typeReclamationRepository;

    @Autowired
    private SousTypeReclamationRepository sousTypeReclamationRepository;

    @Override
    public List<TypeReclamation> getAllTypesReclamations() {
        return typeReclamationRepository.findAll();
    }

    @Override
    public List<SousTypeReclamation> findSousTypeReclamationsOfTypeReclamation(Long id) {

        TypeReclamation typeReclamation=typeReclamationRepository.findById(id).get();
        return sousTypeReclamationRepository.findByTypeReclamation(typeReclamation);
    }
}
