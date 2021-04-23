package com.nofluffkitchen.sanders.data;

import com.nofluffkitchen.sanders.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);
}