package com.aleklew.ballot.modules.general.dbmodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "BallotAnswers")
@JsonIdentityInfo(property = "answerID", generator = ObjectIdGenerators.PropertyGenerator.class)
public class BallotAnswer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BallotAnswerID")
	private int answerID;

	@ManyToOne
	@JoinColumn(name = "BallotQuestionID")
	@JsonIdentityReference(alwaysAsId = true)
    private BallotQuestion question;

	@Column(name = "ResponderToken")
	private int responderToken;

	@Column(name = "AnswerText")
	private String answerText;

	@Column(name = "AnswerNumber")
	private int answerNumber;
	
	public int getID() {
		return answerID;
	}

	public BallotQuestion getQuestion() {
		return question;
	}

	public Ballot getBallot() {
		return question.getBallot();
	}

	public int getResponderToken() {
		return responderToken;
	}

	public String getAnswerText() {
		return answerText;
	}

	public int getAnswerNumber() {
		return answerNumber;
	}
}
