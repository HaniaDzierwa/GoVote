package com.aleklew.ballot.modules.profiles.interfaces;

import com.aleklew.ballot.modules.profiles.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
}
