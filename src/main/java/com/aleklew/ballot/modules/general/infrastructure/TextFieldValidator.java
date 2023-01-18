package com.aleklew.ballot.modules.general.infrastructure;

import com.aleklew.ballot.modules.general.models.validation.FieldValidationResult;

public class TextFieldValidator {

    public static String NUMBER_FIELD_REGEX = "^[0-9\\s-_]+$";
    public static String TEXT_FIELD_REGEX = "^[\\p{L}\\s-_,\\.]+$";
    public static String MIX_FIELD_REGEX = "^[0-9\\p{L}\\s-_,\\.]+$";
    public static String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#\\.])[A-Za-z\\d@$!%*?&#\\.]{8,}$";
    public static String ALPHA_NUMERIC_REGEX =  "^[0-9A-Za-z\\s]+$";
    public static String PHONE = "^[0-9\\s-_]{6,12}$";

    public static String EMAIL_ADDRESS = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+";

    public static FieldValidationResult validateField(String content, String fieldName, FieldType fieldType, int length, boolean required, int min) {
        FieldValidationResult result = validateField(content, fieldName, fieldType, length, required);
        if (!result.isValid())
            return result;

        if (length < min)
            return new FieldValidationResult(false, "Nie poprawna długość", fieldName);

        return result;
    }

    public static FieldValidationResult validateField(String content, String fieldName, FieldType fieldType, int length, boolean required) {

        if (!required && (content == null || content.length() == 0))
            return new FieldValidationResult(true, fieldName);

        if ((content == null || content.length() == 0) && required)
            return new FieldValidationResult(false, "Pole wymagane", fieldName);

        if (content.length() > length)
            return new FieldValidationResult(false, "Nie poprawna długość", fieldName);


        switch (fieldType) {
            case MIX:
                if (!content.matches(MIX_FIELD_REGEX))
                    return new FieldValidationResult(false, "Pole mieszane pozwala na cyfry 0-9, litery a-z A-Z, spacje, przecinki oraz kropki.", fieldName);
                break;
            case PASSWORD:
                if (!content.matches(PASSWORD_REGEX))
                    return new FieldValidationResult(false, "Hasło wymaga cyfry 0-9, liter a-z A-Z oraz znaku specjalnego @$!%*?&amp;#., Minimalna długość to 8 znaków.", fieldName);
                break;
            case TEXT:
                if (!content.matches(TEXT_FIELD_REGEX))
                    return new FieldValidationResult(false, "Pola tesktowe pozwalają na litery a-z A-Z, spacje, kropki i przecinki.", fieldName);
                break;
            case NUMBER:
                if (!content.matches(NUMBER_FIELD_REGEX))
                    return new FieldValidationResult(false, "Pole numeryczne pozwala na cyfry 0-9 oraz spację.", fieldName);
                break;
            case EMAIL:
                if (!content.matches(EMAIL_ADDRESS))
                    return new FieldValidationResult(false, "Niepoprawny adres e-mail.", fieldName);
                break;
            case PHONE:
                if (!content.matches(PHONE))
                    return new FieldValidationResult(false, "Niepoprawny numer telefonu.", fieldName);
                break;
            case ALPHA_NUMERIC_REGEX:
                if (!content.matches(ALPHA_NUMERIC_REGEX))
                    return new FieldValidationResult(false, "Niepoprawna zawartość pola.", fieldName);
                break;
        }

        return new FieldValidationResult(true, fieldName);
    }

    public static FieldValidationResult validateField(String content, String fieldName, FieldType fieldType, int length, boolean required, String pattern) {
        FieldValidationResult baseValidation = validateField(content, fieldName, fieldType, length, required);
        if (!baseValidation.isValid() || pattern == null)
            return baseValidation;

        if (content.matches(pattern))
            return new FieldValidationResult(true, fieldName);

        return new FieldValidationResult(false, "Niepoprawna zawartość pola.", fieldName);
    }

    public enum FieldType {
        TEXT, NUMBER, MIX, EMAIL, PHONE, PASSWORD, ALPHA_NUMERIC_REGEX
    }
}