package com.isicod.net.communitiesManagement.repositories;

import com.isicod.net.communitiesManagement.models.Community;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<Community, Long> {
}
