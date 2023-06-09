package com.aleklew.ballot.modules.general.controllers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.aleklew.ballot.modules.general.models.test.SimpleTestModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("api/v1/public/test")
public class ApiTest {
	
    @PersistenceContext
    private EntityManager entityManager;

	@PostMapping("/bajo")
    public ResponseEntity<SimpleTestModel> bajo(){
        return new ResponseEntity<>(new SimpleTestModel("description"), HttpStatus.OK);
    }
}
