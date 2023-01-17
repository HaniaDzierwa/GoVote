package com.aleklew.ballot.modules.general.db.dbmodels;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Ballots")
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
@JsonIdentityInfo(property = "ballotId", generator = ObjectIdGenerators.PropertyGenerator.class)
public class BallotEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BallotId")
	private int ballotId;

	@Column(name = "OwnerId")
	private int ownerId;

	@Column(name = "BallotName")
	private String ballotName;

	@Column(name = "BallotDescription")
	private String ballotDescription;


	public BallotEntity(int ownerId, String ballotName, String ballotDescription) {
		this.ownerId = ownerId;
		this.ballotName = ballotName;
		this.ballotDescription = ballotDescription;
	}
}

