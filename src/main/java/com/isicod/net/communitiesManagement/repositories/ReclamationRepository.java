package com.isicod.net.communitiesManagement.repositories;

import com.isicod.net.communitiesManagement.models.Citoyen;
import com.isicod.net.communitiesManagement.models.Reclamation;
import com.isicod.net.communitiesManagement.models.Suggestion;
import com.isicod.net.communitiesManagement.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation,Long> {

    public List<Reclamation> findByUsers(Users users);

    static final String RECLAMATIONS_OF_GERANT="select r from Reclamation r where r.typeReclamation.profil" +
            ".id=:id and status.code=:status ";

    @Query(RECLAMATIONS_OF_GERANT)
    public List<Reclamation> getReclamationsOfGerant(@Param("id") Long idProfil,@Param("status") String status);

    static final String RECLAMATIONS_OF_PRESIDENT="Select r from Reclamation r where (r.seenByGerant=null OR r.seenByGerant=false) " +
            "AND EXTRACT(EPOCH FROM (now() - r.createdAt)) >= :secondes";

    @Query(RECLAMATIONS_OF_PRESIDENT)
    public List<Reclamation> getReclamationsOfPresident(int secondes);

    @Query(value = RECLAMATION_BY_STATUS,nativeQuery = true)
    public List<Reclamation> getReclamationByStatus(@Param("idUser") Long idUsers,@Param("status") String status );
    static final String RECLAMATION_BY_STATUS="select * from reclamation rec, status st where rec.status_id=st.id "+
    "and st.code=:status and rec.users_id=:idUser ";

    @Query(value = GET_RECLAMATION_NON_SATIFAIT,nativeQuery = true)
    public List<Reclamation> getReclamationNonSatisfait(@Param("idUser") Long idUsers);
    static final String GET_RECLAMATION_NON_SATIFAIT="select * from reclamation rec , type_Reclamation ty , profil pr " +
            "  where rec.type_reclamation_id=ty.id " +
            "   and ty.profil_id=pr.id " +
            "  and pr.id=:idUser and rec.satisfait='N' ";
}
