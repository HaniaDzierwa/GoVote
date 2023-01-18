package com.aleklew.ballot.modules.ballot.models;

import com.aleklew.ballot.modules.ballot.dbmodels.BallotEntity;
import com.aleklew.ballot.modules.ballot.dbmodels.BallotQuestionEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
public class BallotRestModel {
    String ballotDescription;
    String ballotName;
    int ownerId;
    Set<BallotQuestionEntity> questions;

    public static BallotRestModel fromEntity(BallotEntity ballotEntity) {
        return new BallotRestModel(
                ballotEntity.getBallotDescription(),
                ballotEntity.getBallotName(),
                ballotEntity.getOwnerId(),
                new HashSet<>()
        );
    }
}
