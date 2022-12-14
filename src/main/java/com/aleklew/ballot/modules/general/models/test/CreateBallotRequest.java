package com.aleklew.ballot.modules.general.models.test;

public class CreateBallotRequest {
    private String ballotDescription;
    private String ballotName;
    private int ownerID;

    public CreateBallotRequest(String ballotDescription, String ballotName, int ownerID) {
        this.ballotDescription = ballotDescription;
        this.ballotName = ballotName;
        this.ownerID = ownerID;
    }

    public String getBallotDescription() {
        return ballotDescription;
    }

    public void setBallotDescription(String ballotDescription) {
        this.ballotDescription = ballotDescription;
    }

    public String getBallotName() {
        return ballotName;
    }

    public void setBallotName(String ballotName) {
        this.ballotName = ballotName;
    }

    public int getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }
}
