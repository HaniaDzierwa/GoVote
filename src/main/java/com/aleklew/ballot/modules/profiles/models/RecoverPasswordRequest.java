package com.aleklew.ballot.modules.profiles.models;

import com.aleklew.ballot.modules.general.infrastructure.TextFieldValidator;
import com.aleklew.ballot.modules.general.models.validation.FieldValidationResult;
import com.aleklew.ballot.modules.general.models.validation.FormValidationResult;

import java.util.ArrayList;
import java.util.List;

public class RecoverPasswordRequest {

    private String activationCode;
    private String newPassword;

    public RecoverPasswordRequest() {
    }

    public RecoverPasswordRequest(String activationCode, String newPassword) {
        this.activationCode = activationCode;
        this.newPassword = newPassword;
    }

    public FormValidationResult validateRequest() {
        List<FieldValidationResult> validationResults = new ArrayList<>();

        validationResults.add(TextFieldValidator.validateField(newPassword, "Hasło", TextFieldValidator.FieldType.PASSWORD, 25, true, 8));

        boolean valid = validationResults.stream().allMatch(FieldValidationResult::isValid);
        return new FormValidationResult(valid, validationResults);
    }

    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
