package com.aleklew.ballot.modules.general.models.validation;

public class FieldValidationResult {
    private boolean valid;
    private String errMessage;
    private String associatedFieldName;

    public FieldValidationResult(boolean valid, String errMessage, String associatedFieldName) {
        this.valid = valid;
        this.errMessage = errMessage;
        this.associatedFieldName = associatedFieldName;
    }

    public FieldValidationResult(boolean valid, String associatedFieldName) {
        this.valid = valid;
        this.associatedFieldName = associatedFieldName;
    }

    // region GETTERS AND SETTERS

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }

    public String getAssociatedFieldName() {
        return associatedFieldName;
    }

    public void setAssociatedFieldName(String associatedFieldName) {
        this.associatedFieldName = associatedFieldName;
    }
}
