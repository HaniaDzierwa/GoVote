package com.aleklew.ballot.modules.profiles.controllers;

import com.aleklew.ballot.modules.profiles.interfaces.RoleRepository;
import com.aleklew.ballot.modules.profiles.interfaces.UserRepository;
import com.aleklew.ballot.modules.profiles.dbmodels.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
* For admins -> need to restrict
* */
@RestController
@RequestMapping("/api/v1/admin/user")
@PreAuthorize("hasRole('admin')")
public class AdminUserController {
	private UserRepository userRepository;

	public AdminUserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/getNumber")
    public ResponseEntity<String> getNumber()
    {
        return new ResponseEntity<>("num users: " + userRepository.count(), HttpStatus.OK);
    }

	@GetMapping("/getUserByID")
	public ResponseEntity<User> getUserByID(@RequestParam Integer id)
	{
		Optional<User> user = userRepository.findById(id);
		return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/getAllUsers")
	@ResponseBody
	public List<User> getAllUsers()
	{
		return userRepository.findAll();
	}

}
