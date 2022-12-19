package com.isicod.net.communitiesManagement.repositories;

import com.isicod.net.communitiesManagement.models.Photos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotosRepository extends JpaRepository<Photos,Long> {
}
