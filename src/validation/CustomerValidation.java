package validation;

import entities.Gender;
import exceptions.FailedValidationException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class CustomerValidation {
    public boolean isValidName (String name){
        if (name.length() > 30) {
            try {
                throw new FailedValidationException("Name should contains less than 30 character");
            } catch (FailedValidationException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean isValidDOB (String doB){
        Locale locale = new Locale("en");
        LocalDate ld = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy",locale );
            ld = LocalDate.parse(doB, formatter);
            String res = ld.format(formatter);

            if (!res.equals(doB)) {
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
    public boolean isValidGender (Gender gender){
        if (!(gender.equals(Gender.MALE) || gender.equals(Gender.FEMALE) || gender.equals(Gender.SOMETHING_ELSE))) {
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

    public boolean isValidPhoneNumber (String number){
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
