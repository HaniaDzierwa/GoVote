package com.aleklew.ballot.modules.ballot.dbmodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

@Entity
@Table(name = "BallotAnswers")
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@With
@JsonIdentityInfo(property = "answerId", generator = ObjectIdGenerators.PropertyGenerator.class)
public class BallotAnswerEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BallotAnswerId")
	private int answerId;

	@ManyToOne
	@JoinColumn(name = "BallotQuestionId")
    private BallotQuestionEntity questionId;

	@Column(name = "ResponderToken")
	private int responderToken;

	@Column(name = "AnswerContent")
	private String answerText;

	@Column(name = "AnswerOrderNumber")
	private int answerOrderNumber;
}
