package com.aleklew.ballot.modules.profiles.interfaces;

import com.aleklew.ballot.modules.profiles.dbmodels.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	
}
