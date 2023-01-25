package com.aleklew.ballot.modules.ballot.dbmodels;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    @Column(name = "Published")
    private boolean published = false;

    public BallotEntity(int ownerId, String ballotName, String ballotDescription, boolean published) {
        this.ownerId = ownerId;
        this.ballotName = ballotName;
        this.ballotDescription = ballotDescription;
        this.published = published;
    }
}

