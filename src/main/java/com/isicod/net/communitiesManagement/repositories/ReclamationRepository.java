package com.isicod.net.communitiesManagement.repositories;

import com.isicod.net.communitiesManagement.models.Citoyen;
import com.isicod.net.communitiesManagement.models.Reclamation;
import com.isicod.net.communitiesManagement.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {

    public List<Reclamation> findByUsers(Users users);

    static final String RECLAMATIONS_OF_GERANT="select r from Reclamation r where r.typeReclamation.profil" +
            ".id=:id";

    @Query(RECLAMATIONS_OF_GERANT)
    public List<Reclamation> getReclamationsOfGerant(@Param("id") Long idProfil);

    static final String RECLAMATIONS_OF_PRESIDENT="Select r from Reclamation r where (r.seenByGerant=null OR r.seenByGerant=false) " +
            "AND EXTRACT(EPOCH FROM (now() - r.createdAt)) >= :secondes";

    @Query(RECLAMATIONS_OF_PRESIDENT)
    public List<Reclamation> getReclamationsOfPresident(int secondes);
}
