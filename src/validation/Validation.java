package validation;

import entities.Gender;
import exceptions.FailedValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validation {

    public static boolean isValidNumericDataInString(String num) {
        // check if string contains only positive digitals
        if (!num.matches("^\\d+$")) {
            try {
                throw new FailedValidationException("Invalid input. It should contains only positive digitals");
            } catch (FailedValidationException e) {
                e.printStackTrace();
                return false;


            }
        } else {
            return true;
        }
    }

    public static boolean isValidStringLength(String str, int length) {
        if (str.length() > length) {
            try {
                throw new FailedValidationException("Length of string should be less than" + length + " character");
            } catch (FailedValidationException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }

    public static boolean isValidDateFormat(String date, DateTimeFormatter formatter) {
        LocalDate ld = null;

        try {
            ld = LocalDate.parse(date, formatter);
            String res = ld.format(formatter);

            if (!res.equals(date)) {
                try {
                    throw new FailedValidationException("Invalid format of date");
                } catch (FailedValidationException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean isValidDateTimeFormat(String date, DateTimeFormatter formatter) {
        LocalDateTime ldt = null;

        try {
            ldt = LocalDateTime.parse(date, formatter);
            String res = ldt.format(formatter);

            if (!res.equals(date)) {
                try {
                    throw new FailedValidationException("Invalid format of date and time");
                } catch (FailedValidationException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean isValidGender (Gender gender){
        if (!( gender.equals(Gender.MALE) || gender.equals(Gender.FEMALE) || gender.equals(Gender.SOMETHING_ELSE))) {
            try {
                throw new FailedValidationException("Invalid value for \"Gender\"");
            } catch (FailedValidationException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }

    public static boolean isValidPhoneNumber (String number){
        if (number.matches("^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")) {
            return true;
        } else {
            try {
                throw new FailedValidationException("Invalid telephone number");
            } catch (FailedValidationException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
}