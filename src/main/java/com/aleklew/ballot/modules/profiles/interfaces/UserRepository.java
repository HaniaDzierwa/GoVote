package com.aleklew.ballot.modules.profiles.interfaces;

import com.aleklew.ballot.modules.profiles.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
	
}
