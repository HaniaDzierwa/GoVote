package com.aleklew.ballot.modules.ballot.controllers;

import com.aleklew.ballot.modules.ballot.dbmodels.BallotQuestionEntity;
import com.aleklew.ballot.modules.ballot.interfaces.BallotQuestionService;
import com.aleklew.ballot.modules.ballot.models.CreateQuestionRequest;
import com.aleklew.ballot.modules.profiles.dbmodels.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/ballotQuestion")
@PreAuthorize("hasRole('admin') or hasRole('user')")
public class BallotQuestionController {

    private BallotQuestionService ballotQuestionCreatorService;

    public BallotQuestionController(BallotQuestionService ballotQuestionCreatorService) {
        this.ballotQuestionCreatorService = ballotQuestionCreatorService;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Boolean> deleteBallotQuestion(@RequestParam int questionId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // TODO: olalew permissions/auth

        if (ballotQuestionCreatorService.deleteBallotQuestion(questionId)) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.ok(false);
    }

    @PostMapping("/create")
    public ResponseEntity<BallotQuestionEntity> createBallotQuestion(@RequestBody CreateQuestionRequest request)
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // assign to user ?

        BallotQuestionEntity createdQuestion = ballotQuestionCreatorService.createBallotQuestion(request);
        if(createdQuestion != null) {
            return ResponseEntity.ok(createdQuestion);
        }
        return ResponseEntity.ok().build();
    }
    @GetMapping("/getById")
    public ResponseEntity<BallotQuestionEntity> getBallotQuestionByID(@RequestParam int questionId)
    {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // if assigned to user ?

        BallotQuestionEntity question = ballotQuestionCreatorService.getBallotQuestionById(questionId);
        if(question != null) {
            return ResponseEntity.ok(question);
        }
        return  ResponseEntity.ok().build();
    }

    @GetMapping("/getQuestions")
    public ResponseEntity<Set<BallotQuestionEntity>> getQuestionsByBallotId(int ballotId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // if assigned to user

       Set<BallotQuestionEntity> questions = ballotQuestionCreatorService.getQuestionsByBallotId(ballotId);
        if(questions != null) {
            return ResponseEntity.ok(questions);
        }
        return ResponseEntity.ok().build();
    }
}



