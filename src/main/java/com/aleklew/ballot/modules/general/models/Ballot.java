package com.aleklew.ballot.modules.general.models;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.aleklew.ballot.modules.profiles.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Ballots")
public class Ballot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BallotID")
	private int ballotID;

	@ManyToOne
	@JoinColumn(name = "OwnerID", referencedColumnName = "UserID")
	private User owner;

	@Column(name = "BallotName")
	private String ballotName;

	@Column(name = "BallotDescription")
	private String ballotDescription;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "ballot")
	private Set<BallotQuestion> questions;

	public int getBallotID() {
		return ballotID;
	}

	public User getOwner() {
		return owner;
	}

	public String getBallotName() {
		return ballotName;
	}

	public String getBallotDescription() {
		return ballotDescription;
	}

	public Set<BallotQuestion> getQuestions() {
		return questions;
	}
}
