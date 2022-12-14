package com.isicod.net.communitiesManagement.repositories;

import com.isicod.net.communitiesManagement.models.SousTypeReclamation;
import com.isicod.net.communitiesManagement.models.TypeReclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SousTypeReclamationRepository extends JpaRepository<SousTypeReclamation,Long> {

    public List<SousTypeReclamation> findByTypeReclamation(TypeReclamation typeReclamation);
}
