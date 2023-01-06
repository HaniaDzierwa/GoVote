package com.aleklew.ballot.modules.general.rest.dto;

import lombok.Value;

@Value
public class CreateBallotRequest {
    String ballotDescription;
    String ballotName;
    int ownerId;
}
