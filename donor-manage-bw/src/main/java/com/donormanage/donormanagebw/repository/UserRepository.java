package com.donormanage.donormanagebw.repository;

import com.donormanage.donormanagebw.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long>
{
    User findByUsername(String username);
}
