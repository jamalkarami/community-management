package com.isicod.net.communitiesManagement.services;

import com.isicod.net.communitiesManagement.models.SousTypeReclamation;
import com.isicod.net.communitiesManagement.models.TypeReclamation;

import java.util.List;

public interface ReferencielService {

    public List<TypeReclamation> getAllTypesReclamations();

    public List<SousTypeReclamation> findSousTypeReclamationsOfTypeReclamation(Long id);
}
