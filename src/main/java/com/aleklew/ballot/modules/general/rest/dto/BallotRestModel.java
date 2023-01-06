package com.aleklew.ballot.modules.general.rest.dto;

import com.aleklew.ballot.modules.general.db.dbmodels.BallotEntity;
import com.aleklew.ballot.modules.general.db.dbmodels.BallotQuestionEntity;
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
