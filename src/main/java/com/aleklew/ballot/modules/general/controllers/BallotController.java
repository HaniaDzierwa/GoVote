package com.aleklew.ballot.modules.general.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aleklew.ballot.modules.general.interfaces.BallotAnswerRepository;
import com.aleklew.ballot.modules.general.interfaces.BallotQuestionRepository;
import com.aleklew.ballot.modules.general.interfaces.BallotRepository;
import com.aleklew.ballot.modules.general.models.Ballot;
import com.aleklew.ballot.modules.general.models.BallotAnswer;
import com.aleklew.ballot.modules.general.models.BallotQuestion;
import com.aleklew.ballot.modules.profiles.interfaces.UserRepository;
import com.aleklew.ballot.modules.profiles.models.Role;
import com.aleklew.ballot.modules.profiles.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@RestController
@RequestMapping("/api/v1/ballot")
public class BallotController {
	@Autowired
	private BallotRepository ballotRepository;

	@Autowired
	private BallotQuestionRepository ballotQuestionRepository;

	@Autowired
	private BallotAnswerRepository ballotAnswerRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/create")
	public ResponseEntity<Ballot> create(@RequestBody Ballot ballot) {
		// TODO: olalew permissions/auth
		ballot = ballotRepository.save(ballot);
		return ResponseEntity.ok(ballot);
	}

	@PostMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Ballot ballot) {
		// TODO: olalew permissions/auth
		try {
			ballotRepository.deleteById(ballot.getBallotID());
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/addQuestion")
	public ResponseEntity<BallotQuestion> addQuestion(@RequestBody BallotQuestion ballotQuestion) {
		// TODO: olalew permissions/auth
		ballotQuestion = ballotQuestionRepository.save(ballotQuestion);
		return ResponseEntity.ok(ballotQuestion);
	}

	@PostMapping("/deleteQuestion")
	public ResponseEntity<?> deleteQuestion(@RequestBody BallotQuestion ballotQuestion) {
		// TODO: olalew permissions/auth
		try {
			ballotQuestionRepository.deleteById(ballotQuestion.getQuestionID());
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	@PostMapping("/addAnswer")
	public ResponseEntity<BallotAnswer> addAnswer(@RequestBody BallotAnswer ballotAnswer) {
		// TODO: olalew permissions/auth
		ballotAnswer= ballotAnswerRepository.save(ballotAnswer);
		return ResponseEntity.ok(ballotAnswer);
	}

	@PostMapping("/getBallotByID")
	public ResponseEntity<Ballot> getBallotByID(@RequestBody Ballot ballot) {
		// TODO: olalew permissions/auth
		ballot = ballotRepository.getReferenceById(ballot.getBallotID());
		return ResponseEntity.ok(ballot);
	}


}
