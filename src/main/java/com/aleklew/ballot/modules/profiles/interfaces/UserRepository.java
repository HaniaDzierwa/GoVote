package com.aleklew.ballot.modules.profiles.interfaces;

import com.aleklew.ballot.modules.profiles.dbmodels.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Cacheable
    Optional<User> findByUserName(String userName);

    @Cacheable
    Optional<User> findByEmail(String email);

    @Cacheable
    Optional<User> findByActivationCode(String activationCode);
}
