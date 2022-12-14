package com.aleklew.ballot.modules.general.db.dbmodels;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "BallotQuestions")
@JsonIdentityInfo(property = "questionID", generator = ObjectIdGenerators.PropertyGenerator.class)
public class BallotQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BallotQuestionID")
    private int questionID;

	@ManyToOne
	@JoinColumn(name = "BallotID")
	@JsonIdentityReference(alwaysAsId = true)
    private Ballot ballot;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "question", fetch = FetchType.EAGER)
	private Set<BallotAnswer> answers;

	@Column(name = "QuestionNumber")
    private int questionNumber;

	@Column(name = "Heading")
    private String heading;

	@Column(name = "Text")
    private String text;

    public int getQuestionID() {
		return questionID;
	}

    public Ballot getBallot() {
		return ballot;
	}

    public int getQuestionNumber() {
		return questionNumber;
	}

    public String getHeading() {
		return heading;
	}

	public String getText() {
		return text;
	}

	public Set<BallotAnswer> getAnswers() {
		return answers;
	}
}
