package com.aleklew.ballot.modules.ballot.models;

import lombok.Value;

@Value
public class CreateBallotRequest {
    String ballotDescription;
    String ballotName;
    int ownerId;
    boolean published;
}
