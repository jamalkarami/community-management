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

    static final String RECLAMATIONS_OF_GERANT_TRS="select r from Reclamation r where r.typeReclamation.profil" +
            ".id=:id and r.status.code=:status and r.satisfait=null";

    @Query(RECLAMATIONS_OF_GERANT_TRS)
    public List<Reclamation> getReclamationsOfGerantTRS(@Param("id") Long idProfil,@Param("status") String status);

    static final String RECLAMATIONS_OF_GERANT_TRNS="select r from Reclamation r where r.typeReclamation.profil" +
            ".id=:id and r.status.code=:status and r.satisfait='N'";

    @Query(RECLAMATIONS_OF_GERANT_TRNS)
    public List<Reclamation> getReclamationsOfGerantTRNS(@Param("id") Long idProfil,@Param("status") String status);

    static final String RECLAMATIONS_OF_PRESIDENT="Select r from Reclamation r where (r.seenByGerant=null OR r.seenByGerant=false) " +
            "AND EXTRACT(EPOCH FROM (now() - r.createdAt)) >= :secondes and status.code='EV' ";

    @Query(RECLAMATIONS_OF_PRESIDENT)
    public List<Reclamation> getReclamationsOfPresident(int secondes);

    @Query(value = RECLAMATION_BY_STATUS,nativeQuery = true)
    public List<Reclamation> getReclamationByStatus(@Param("idUser") Long idUsers,@Param("status") String status );
    static final String RECLAMATION_BY_STATUS="select * from reclamation rec, status st where rec.status_id=st.id "+
    "and st.code=:status and rec.users_id=:idUser ";

    @Query(value = RECLAMATION_BY_STATUS_NON_SATISFAIT,nativeQuery = true)
    public List<Reclamation> getReclamationByStatusAndNoSatisfait(@Param("idUser") Long idUsers );
    static final String RECLAMATION_BY_STATUS_NON_SATISFAIT="select rec.* from reclamation rec, status st  , type_reclamation tr , profil p " +
            "where rec.status_id=st.id  " +
            "and rec.type_reclamation_id=tr.id " +
            "and p.id=tr.profil_id " +
            "and st.code='TR' and p.id=:idUser and satisfait='N' ";



//    @Query(value = RECLAMATION_BY_STATUS_NON_SATISFAIT,nativeQuery = true)
//    public List<Reclamation> getReclamationByStatusAndNoSatisfait(@Param("idUser") Long idUsers );
//    static final String RECLAMATION_BY_STATUS_NON_SATISFAIT="select * from reclamation rec, status st where rec.status_id=st.id "+
//            "and st.code='TR' and rec.users_id=:idUser and satisfait='N' ";

    @Query(value = GET_RECLAMATION_NON_SATIFAIT,nativeQuery = true)
    public List<Reclamation> getReclamationNonSatisfait();
    static final String GET_RECLAMATION_NON_SATIFAIT="select * from reclamation rec , " +
            "type_Reclamation ty , profil pr , status st " +
            "  where rec.type_reclamation_id=ty.id " +
            "  and rec.status_id=st.id "  +
            " and st.code='TR' " +
            "   and ty.profil_id=pr.id " +
            "  and rec.satisfait='N' ";

    @Query(GET_RECLAMATION_BY_STATUS_AND_NON_SATISFAIT)
    public List<Reclamation> getReclamationByStatusAndNonSatifait(@Param("id") Long idProfil);

    static final String GET_RECLAMATION_BY_STATUS_AND_NON_SATISFAIT="select r from Reclamation r where r.typeReclamation.profil" +
            ".id=:id and status.code ='TR' and satisfait='N'";
}
