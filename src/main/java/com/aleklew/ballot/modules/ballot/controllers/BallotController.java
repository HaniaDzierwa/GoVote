package com.aleklew.ballot.modules.ballot.controllers;

import com.aleklew.ballot.modules.ballot.dbmodels.BallotEntity;
import com.aleklew.ballot.modules.ballot.dbmodels.CompletedBallotEntity;
import com.aleklew.ballot.modules.ballot.interfaces.BallotQuestionService;
import com.aleklew.ballot.modules.ballot.interfaces.BallotRepository;
import com.aleklew.ballot.modules.ballot.interfaces.BallotService;
import com.aleklew.ballot.modules.ballot.models.BallotRestModel;
import com.aleklew.ballot.modules.ballot.models.CompletedBallotRequest;
import com.aleklew.ballot.modules.ballot.models.CreateBallotRequest;
import com.aleklew.ballot.modules.ballot.models.QuestionWithAnswerResponse;
import com.aleklew.ballot.modules.profiles.dbmodels.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:8080", "http://localhost:4200"},
        exposedHeaders = "Access-Control-Allow-Origin")

@RestController
@RequestMapping("/api/v1/ballot")
@PreAuthorize("hasRole('admin') or hasRole('user')")
public class BallotController {
    private final BallotService ballotService;
    private final BallotQuestionService ballotQuestionService;
    private final BallotRepository ballotRepository;

    public BallotController(BallotRepository ballotRepository,
                            BallotQuestionService ballotQuestionService,
                            BallotService ballotService

    ) {
        this.ballotRepository = ballotRepository;
        this.ballotQuestionService = ballotQuestionService;
        this.ballotService = ballotService;

    }

    @PostMapping("/create")
    public ResponseEntity<BallotEntity> createBallot(@RequestBody CreateBallotRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // TODO: olalew permissions/auth

        if (ballotService.deleteBallot(ballotId)) {
            return ResponseEntity.ok("deleted");
        }
        return ResponseEntity.ok("not found");
    }

    @GetMapping("/getById")
    public ResponseEntity<BallotEntity> getBallotById(@RequestParam int ballotId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // TODO: olalew permissions/auth
        BallotEntity ballot = ballotService.getBallotById(ballotId);
        if (ballot != null) {
            return ResponseEntity.ok(ballot);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("getBallotWithQuestions")
    public ResponseEntity<BallotRestModel> getBallotByIdWithQuestions(@RequestParam int ballotId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        BallotEntity ballotEntity = ballotService.getBallotById(ballotId);
        BallotRestModel ballotRestModel = BallotRestModel.fromEntity(ballotEntity);

        ballotRestModel.setQuestions(ballotQuestionService.getQuestionsByBallotId(ballotId));

        if (ballotRestModel != null) {
            return ResponseEntity.ok(ballotRestModel);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/Ballots")
    public ResponseEntity<List<BallotEntity>> getBallots() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(ballotRepository.findAll());
    }

    @PostMapping("/publish")
    public ResponseEntity<BallotRestModel> publishBallot(@RequestParam int ballotId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ballotService.publishBallotById(ballotId)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/completeBallot")
    public ResponseEntity<CompletedBallotEntity> completeBallot(@RequestBody CompletedBallotRequest completedBallotRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (ballotService.completeBallot(completedBallotRequest, user.getID())) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/completedBallot/{ballotId}")
    public ResponseEntity<List<QuestionWithAnswerResponse>> completedBallot(@PathVariable int ballotId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var questionWithAnswerByBallotId = ballotService.getQuestionWithAnswerByBallotId(ballotId, user.getID());
        if (questionWithAnswerByBallotId != null) {
            return ResponseEntity.ok(questionWithAnswerByBallotId);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/completedBallots")
    public ResponseEntity<List<BallotEntity>> completedBallots() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        var completedBallotEntities = ballotService.getCompletedBallots(user.getID());
        if (completedBallotEntities != null) {
            return ResponseEntity.ok(completedBallotEntities);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/notCompletedBallots")
    public ResponseEntity<List<BallotEntity>> notCompletedBallots() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		var completedBallotEntities = ballotService.getNotCompletedBallots(user.getID());
        if (completedBallotEntities != null) {
            return ResponseEntity.ok(completedBallotEntities);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
