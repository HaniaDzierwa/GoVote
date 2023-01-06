package com.aleklew.ballot.modules.general.rest.controllers;

import com.aleklew.ballot.modules.general.db.dbmodels.BallotQuestionEntity;
import com.aleklew.ballot.modules.general.interfaces.BallotQuestionService;
import com.aleklew.ballot.modules.general.rest.dto.CreateQuestionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/ballotQuestion")
public class BallotQuestionController {

    @Autowired
    private BallotQuestionService ballotQuestionCreatorService;

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteBallotQuestion(@RequestParam int questionId) {
        // TODO: olalew permissions/auth

        if (ballotQuestionCreatorService.deleteBallotQuestion(questionId)) {
            return ResponseEntity.ok("deleted");
        }
        return ResponseEntity.ok("not deleted");
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBallotQuestion(@RequestBody CreateQuestionRequest request)
    {
        BallotQuestionEntity createdQuestion = ballotQuestionCreatorService.createBallotQuestion(request);
        if(createdQuestion != null) {
            return ResponseEntity.ok(createdQuestion);
        }
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getById")
    public ResponseEntity<BallotQuestionEntity> getBallotQuestionByID (@RequestParam int questionId)
    {
        BallotQuestionEntity question = ballotQuestionCreatorService.getBallotQuestionById(questionId);
        if(question != null) {
            return ResponseEntity.ok(question);
        }
        return  ResponseEntity.ok().build();
    }

    @GetMapping("/getQuestions")
    public ResponseEntity<Set<BallotQuestionEntity>> getQuestionsByBallotId(int ballotId) {
       Set<BallotQuestionEntity> questions = ballotQuestionCreatorService.getQuestionsByBallotId(ballotId);
        if(questions != null) {
            return ResponseEntity.ok(questions);
        }
        return ResponseEntity.ok().build();
    }
}



