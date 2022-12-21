package com.isicod.net.communitiesManagement.services;

import com.isicod.net.communitiesManagement.models.Pharmacie;

import java.util.List;

public interface PharmacieService {
    public void savePharmacie(Pharmacie pharmacie);

    public List<Pharmacie> getListPharmacie();
}
