package com.aleklew.ballot;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import antlr.collections.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
	@Autowired
    private UserRepository userRepository;

	@Autowired
    private RoleRepository roleRepository;

	@PostMapping("/getNumber")
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

}