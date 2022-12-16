package com.isicod.net.communitiesManagement.repositories;

import com.isicod.net.communitiesManagement.models.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Long> {
    List<News>findByValidation(Boolean validation);
}
