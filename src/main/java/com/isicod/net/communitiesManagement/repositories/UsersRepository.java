package com.isicod.net.communitiesManagement.repositories;

import com.isicod.net.communitiesManagement.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {

    public Users findByTelephoneAndPassword(String telephone,String password);
}
