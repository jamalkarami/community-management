package com.isicod.net.communitiesManagement.repositories;

import com.isicod.net.communitiesManagement.models.Pharmacie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface PharmacieRepository extends JpaRepository<Pharmacie,Long> {

    @Query(value = GET_LIST_PHARMACIE_BY_SUNDAY,nativeQuery = true)
    public List<Pharmacie> getListPharmacie();
    static final String GET_LIST_PHARMACIE_BY_SUNDAY="select * from pharmacie " +
            "where date between current_date and  current_date + extract(dow from current_date+1)::::integer ";
}
