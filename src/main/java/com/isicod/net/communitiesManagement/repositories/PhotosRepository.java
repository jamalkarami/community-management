package com.isicod.net.communitiesManagement.repositories;

import com.isicod.net.communitiesManagement.models.Photos;
import com.isicod.net.communitiesManagement.models.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PhotosRepository extends JpaRepository<Photos,Long> {
//    List<Photos> findByReclamation(Long id);

    @Query(value = FIND_BY_RECLAMATION,nativeQuery = true)
    public  List<Photos> findByReclamation(@Param("idReclamation") Long idReclamation );
    static final String FIND_BY_RECLAMATION="select * from photos  where reclamation_id=:idReclamation ";


    @Query(value = FIND_BY_SUGGESTION,nativeQuery = true)
    public  List<Photos> findBySuggestion(@Param("idSuggestion") Long idSuggestion );
    static final String FIND_BY_SUGGESTION="select * from photos  where suggestion_id=:idSuggestion ";

    @Query(value = FIND_BY_NEWS,nativeQuery = true)
    public  List<Photos> findByNews(@Param("idNews") Long idNews );
    static final String FIND_BY_NEWS="select * from photos  where news_id=:idNews ";
}
