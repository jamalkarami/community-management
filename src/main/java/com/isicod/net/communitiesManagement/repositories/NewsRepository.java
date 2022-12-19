package com.isicod.net.communitiesManagement.repositories;

import com.isicod.net.communitiesManagement.models.News;
import com.isicod.net.communitiesManagement.models.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Long> {
    List<News>findByValidation(Boolean validation);

    @Query(value = FIND_BY_VALIDATION_TRUE,nativeQuery = true)
    public List<News> findByValidationTrue();
    static final String FIND_BY_VALIDATION_TRUE="SELECT * " +
            "FROM public.news where validation = true order by created_at; ";
}
