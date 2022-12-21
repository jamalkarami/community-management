package com.isicod.net.communitiesManagement.services.impl;

import com.isicod.net.communitiesManagement.models.Pharmacie;
import com.isicod.net.communitiesManagement.repositories.PharmacieRepository;
import com.isicod.net.communitiesManagement.services.PharmacieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacieServiceImpl implements PharmacieService {

    @Autowired
    private PharmacieRepository pharmacieRepository;

    @Override
    public void savePharmacie(Pharmacie pharmacie) {
        pharmacieRepository.save(pharmacie);
    }

    @Override
    public List<Pharmacie> getListPharmacie() {
        return pharmacieRepository.getListPharmacie();
    }

    @Override
    public void deletePharmacie(Long id) {
        pharmacieRepository.deleteById(id);
    }
}
