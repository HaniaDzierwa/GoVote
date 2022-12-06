package com.aleklew.ballot;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class ApiTest {
	
    @PersistenceContext
    private EntityManager entityManager;

	@PostMapping("/bajo")
    public ResponseEntity<String> bajojajo(){
        return new ResponseEntity<>("jajo", HttpStatus.OK);
    }
}
