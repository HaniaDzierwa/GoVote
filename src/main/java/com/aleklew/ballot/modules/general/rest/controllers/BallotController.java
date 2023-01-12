package com.aleklew.ballot.modules.general.rest.controllers;

import com.aleklew.ballot.modules.general.db.dbmodels.BallotEntity;
import com.aleklew.ballot.modules.general.interfaces.BallotService;
import com.aleklew.ballot.modules.general.interfaces.BallotQuestionService;
import com.aleklew.ballot.modules.general.rest.dto.BallotRestModel;
import com.aleklew.ballot.modules.general.rest.dto.CreateBallotRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins ={ "http://localhost:8080" ,"http://localhost:4200" },
		exposedHeaders="Access-Control-Allow-Origin")

@RestController
@RequestMapping("/api/v1/ballot")
public class BallotController {
	@Autowired
	private BallotService ballotService;

	@Autowired
	private BallotQuestionService ballotQuestionService;


	@PostMapping ("/create")
	public ResponseEntity<BallotEntity> createBallot(@RequestBody CreateBallotRequest request) {
		// TODO: olalew permissions/auth

		BallotEntity createdBallot = ballotService.createBallot(request);
		if (createdBallot != null) {
			System.out.println("seending");
			return ResponseEntity.ok(createdBallot);
		}
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteBallot(@RequestParam int ballotId) {
		// TODO: olalew permissions/auth

		if (ballotService.deleteBallot(ballotId)) {
			return ResponseEntity.ok("deleted");
		}
		return ResponseEntity.ok("not found");
	}

	@GetMapping("/getById")
	public ResponseEntity<BallotEntity> getBallotById(@RequestParam int ballotId) {
		// TODO: olalew permissions/auth
		BallotEntity ballot = ballotService.getBallotById(ballotId);
		if (ballot != null) {
			return ResponseEntity.ok(ballot);
		}
		return ResponseEntity.ok().build();
	}

	@GetMapping("getBallotWithQuestions")
	public ResponseEntity<BallotRestModel> getBallotByIdWithQuestions (@RequestParam int ballotId) {
		BallotEntity ballotEntity = ballotService.getBallotById(ballotId);
		BallotRestModel ballotRestModel = BallotRestModel.fromEntity(ballotEntity);

		ballotRestModel.setQuestions(ballotQuestionService.getQuestionsByBallotId(ballotId));

		if (ballotRestModel != null) {
			return ResponseEntity.ok(ballotRestModel);
		}
		return ResponseEntity.ok().build();
	}
}
