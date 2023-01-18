package com.aleklew.ballot.modules.ballot.dbmodels;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "BallotQuestions")
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(property = "questionId", generator = ObjectIdGenerators.PropertyGenerator.class)
public class BallotQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BallotQuestionId")
    private int questionId;

	@Column(name = "BallotId")
    private int ballotId;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "questionId", fetch = FetchType.EAGER)
	private Set<BallotAnswerEntity> answers;

	@Column(name = "OrderNumber")
    private int orderNumber;

	@Column(name = "QuestionType")
    private String type;

	@Column(name = "QuestionContent")
    private String questionContent;
}
