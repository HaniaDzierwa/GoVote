package com.aleklew.ballot.modules.general.models.test;

import javax.persistence.*;

@Entity
@Table(name="Test", schema="TestSchema")
public class TestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TestID")
    private int testID;
    @Column(name = "TestDescription")
    private String testDescription;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CreateDate")
    private String createDate;

    public int getTestID() {
        return testID;
    }

    public void setTestID(int testID) {
        this.testID = testID;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
