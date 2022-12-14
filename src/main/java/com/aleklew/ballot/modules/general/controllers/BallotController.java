package com.aleklew.ballot.modules.general.controllers;

import com.aleklew.ballot.modules.general.interfaces.IBallotCreatorService;
import com.aleklew.ballot.modules.general.models.test.CreateBallotRequest;
import com.aleklew.ballot.modules.profiles.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aleklew.ballot.modules.general.db.interfaces.BallotAnswerRepository;
import com.aleklew.ballot.modules.general.db.interfaces.BallotQuestionRepository;
import com.aleklew.ballot.modules.general.db.interfaces.BallotRepository;
import com.aleklew.ballot.modules.general.db.dbmodels.Ballot;
import com.aleklew.ballot.modules.general.db.dbmodels.BallotAnswer;
import com.aleklew.ballot.modules.general.db.dbmodels.BallotQuestion;
import com.aleklew.ballot.modules.profiles.interfaces.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/ballot")
public class BallotController {
	@Autowired
	private IBallotCreatorService ballotCreatorService;

	@PostMapping("/create")
	public ResponseEntity<Ballot> create(@RequestBody CreateBallotRequest request) {
		// TODO: olalew permissions/auth

		Ballot createdBallot = ballotCreatorService.createBallot(request);
		if (createdBallot != null) {
			return ResponseEntity.ok(createdBallot);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestParam int ballotId) {
		// TODO: olalew permissions/auth

		if (ballotCreatorService.deleteBallot(ballotId)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

//	@PostMapping("/addQuestion")
//	public ResponseEntity<BallotQuestion> addQuestion(@RequestBody BallotQuestion ballotQuestion) {
//		// TODO: olalew permissions/auth
//		ballotQuestion = ballotQuestionRepository.save(ballotQuestion);
//		return ResponseEntity.ok(ballotQuestion);
//	}

	@DeleteMapping("/deleteQuestion")
	public ResponseEntity<?> deleteQuestion(@RequestParam int questionId) {
		// TODO: olalew permissions/auth

		if (ballotCreatorService.deleteQuestion(questionId)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

//	@PostMapping("/addAnswer")
//	public ResponseEntity<BallotAnswer> addAnswer(@RequestBody BallotAnswer ballotAnswer) {
//		// TODO: olalew permissions/auth
//		ballotAnswer= ballotAnswerRepository.save(ballotAnswer);
//		return ResponseEntity.ok(ballotAnswer);
//	}
//
//	@PostMapping("/getBallotByID")
//	public ResponseEntity<Ballot> getBallotByID(@RequestBody Ballot ballot) {
//		// TODO: olalew permissions/auth
//		ballot = ballotRepository.getReferenceById(ballot.getBallotID());
//		return ResponseEntity.ok(ballot);
//	}


}
