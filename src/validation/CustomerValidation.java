package validation;

import entities.Gender;
import exceptions.FailedValidationException;

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
        if (number.matches("^[+]*[(]{0,1}[0-9]{1,4}[)]?[-\s\./0-9]*$")) {
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
