package com.isicod.net.communitiesManagement.repositories;

import com.isicod.net.communitiesManagement.models.Reclamation;
import com.isicod.net.communitiesManagement.models.Suggestion;
import com.isicod.net.communitiesManagement.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SuggestionRepository extends JpaRepository<Suggestion,Long> {
    public List<Suggestion> findByUsers(Users users);


    @Query(value = SUGGESTION_OF_GERANT,nativeQuery = true)
    public List<Suggestion> getSuggestionOfGerant(@Param("id") Long idProfil);
    static final String SUGGESTION_OF_GERANT="select * from Suggestion S , profil pr, type_reclamation ty where S.type_suggestion_id=ty.id " +
            "and ty.profil_id= pr.id " +
            "and pr.id=:id";

    static final String SUGGESTION_OF_PRESIDENT="Select r from Suggestion r where (r.seenByGerant=null OR r.seenByGerant=false) " +
            "AND EXTRACT(EPOCH FROM (now() - r.createdAt)) >= :secondes";

    @Query(SUGGESTION_OF_PRESIDENT)
    public List<Suggestion> getReclamationsOfPresident(int secondes);
}
