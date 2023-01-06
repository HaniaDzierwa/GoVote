package com.aleklew.ballot.modules.profiles.controllers;

import com.aleklew.ballot.modules.profiles.interfaces.RoleRepository;
import com.aleklew.ballot.modules.profiles.interfaces.UserRepository;
import com.aleklew.ballot.modules.profiles.models.Role;
import com.aleklew.ballot.modules.profiles.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
    private UserRepository userRepository;

	@Autowired
    private RoleRepository roleRepository;

	@GetMapping("/getNumber")
    public ResponseEntity<String> getNumber()
    {
        return new ResponseEntity<>("num users: " + userRepository.count(), HttpStatus.OK);
    }

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody User user)
	{
		Role role = roleRepository.getReferenceById(2); // Normal user
		user.setRole(role);
		user.setPassword("");
		userRepository.save(user);
		return ResponseEntity.ok(user);
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
