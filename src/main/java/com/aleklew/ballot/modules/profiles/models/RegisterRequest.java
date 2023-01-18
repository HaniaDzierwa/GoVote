package com.aleklew.ballot.modules.profiles.models;

import com.aleklew.ballot.modules.general.infrastructure.TextFieldValidator;
import com.aleklew.ballot.modules.general.models.validation.FieldValidationResult;
import com.aleklew.ballot.modules.general.models.validation.FormValidationResult;

import java.util.ArrayList;
import java.util.List;

public class RegisterRequest {
    private String name;
    private String surname;
    private String login;
    private String password;
    private String email;
    private String PESEL;

    public RegisterRequest(String name, String surname, String login, String password, String email, String PESEL) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.email = email;
        this.PESEL = PESEL;
    }

    public FormValidationResult validateRequest() {
        List<FieldValidationResult> validationResults = new ArrayList<>();

        validationResults.add(TextFieldValidator.validateField(password, "Hasło", TextFieldValidator.FieldType.PASSWORD, 25, true, 8));
        validationResults.add(TextFieldValidator.validateField(email, "Adres Email", TextFieldValidator.FieldType.EMAIL, 100, true));
        validationResults.add(TextFieldValidator.validateField(login, "Login", TextFieldValidator.FieldType.ALPHA_NUMERIC_REGEX, 50, true));

        validationResults.add(TextFieldValidator.validateField(name, "Nazwa", TextFieldValidator.FieldType.TEXT, 25, true, 2));
        validationResults.add(TextFieldValidator.validateField(surname, "Nazwisko", TextFieldValidator.FieldType.TEXT, 25, true, 2));

        validationResults.add(TextFieldValidator.validateField(PESEL, "Pesel", TextFieldValidator.FieldType.NUMBER, 11, false, 0));

        boolean valid = validationResults.stream().allMatch(FieldValidationResult::isValid);
        return new FormValidationResult(valid, validationResults);
    }

    // region GETTERS AND SETTERS

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    // endregion
}
