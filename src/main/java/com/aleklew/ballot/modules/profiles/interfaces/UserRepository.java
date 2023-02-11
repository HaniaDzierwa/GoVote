package com.aleklew.ballot.modules.profiles.interfaces;

import com.aleklew.ballot.modules.profiles.dbmodels.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Cacheable
    Optional<User> findByUserName(String userName);

    @Cacheable
    Optional<User> findByEmail(String email);

    @Cacheable
    Optional<User> findByActivationCode(String activationCode);

    @Query(
            value = "SELECT * FROM Users u WHERE u.ChangePasswordCode = ?1 AND GETDATE() < u.PasswordCodeExpDate",
            nativeQuery = true
    )
    User findUserWithPasswordActionCode(String actionCode);
}
