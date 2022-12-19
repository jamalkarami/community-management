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
}
