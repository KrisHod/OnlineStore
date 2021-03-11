package validation;

import entities.Gender;
import exceptions.FailedValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validator {

    public static boolean isValidNumericDataInString(String num) throws FailedValidationException {
        // check if string contains only positive digitals
        if (!num.matches("^\\d+$")) {
            throw new FailedValidationException("Invalid input. It should contains only positive digitals");
        } else {
            return true;
        }
    }

    public static boolean isValidStringLength(String str, int length) throws FailedValidationException {
        if (str.length() > length) {
            throw new FailedValidationException("Length of string should be less than" + length + " character");
        } else {
            return true;
        }
    }

    public static boolean isValidDateFormat(String date, DateTimeFormatter formatter) throws FailedValidationException {
        LocalDate ld = null;

        try {
            ld = LocalDate.parse(date, formatter);
            String res = ld.format(formatter);

            if (!res.equals(date)) {
                throw new FailedValidationException("Invalid format of date");
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean isValidDateTimeFormat(String date, DateTimeFormatter formatter) throws FailedValidationException {
        LocalDateTime ldt = null;

        try {
            ldt = LocalDateTime.parse(date, formatter);
            String res = ldt.format(formatter);

            if (!res.equals(date)) {
                throw new FailedValidationException("Invalid format of date and time");
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean isValidGender(String gender) throws FailedValidationException {
        if (!(gender.equals(Gender.MALE.toString()) || gender.equals(Gender.FEMALE.toString()) || gender.equals(Gender.SOMETHING_ELSE.toString()))) {
            throw new FailedValidationException("Invalid value for \"Gender\"");
        } else {
            return true;
        }
    }

    public static boolean isValidPhoneNumber(String number) throws FailedValidationException {
        if (!number.isEmpty()) {
            if (number.matches("^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$")) {
                return true;
            } else {
                throw new FailedValidationException("Invalid telephone number");
            }
        }
        return false;
    }
}