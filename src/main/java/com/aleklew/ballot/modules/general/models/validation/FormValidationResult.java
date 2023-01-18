package com.aleklew.ballot.modules.general.models.validation;

import java.util.List;

public class FormValidationResult {

    private boolean valid;
    private List<FieldValidationResult> validationResults;

    public FormValidationResult(boolean valid, List<FieldValidationResult> validationResults) {
        this.valid = valid;
        this.validationResults = validationResults;
    }

    // region GETTERS AND SETTERS

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public List<FieldValidationResult> getValidationResults() {
        return validationResults;
    }

    public void setValidationResults(List<FieldValidationResult> validationResults) {
        this.validationResults = validationResults;
    }

    // endregion
}
