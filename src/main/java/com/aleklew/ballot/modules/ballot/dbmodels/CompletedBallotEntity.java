package com.aleklew.ballot.modules.ballot.dbmodels;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "CompletedBallots")
@AllArgsConstructor
@Setter
@Getter
@Data
@NoArgsConstructor
@Builder
public class CompletedBallotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "userId")
    int userId;

    @Column(name = "ballotId")
    int ballotId;

    @Column(name = "questionId")
    int questionId;

    @Column(name = "answer")
    String answer;
}
