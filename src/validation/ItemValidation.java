package validation;

import exceptions.FailedValidationException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ItemValidation {
    //id;title;code;producer;dateOfLastUpdate

    public boolean isValidId(int id) {
        if (id < 0) {
            try {
                throw new FailedValidationException("Invalid Id");
            } catch (FailedValidationException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean isValidTitle(String title) {
        if (title.length() > 30) {
            try {
                throw new FailedValidationException("Title of item should contains less than 30 character");
            } catch (FailedValidationException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean isValidCode(int code) {
        if (code < 0) {
            try {
                throw new FailedValidationException("Invalid code");
            } catch (FailedValidationException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean isValidProducer(String producer) {
        if (producer.length() > 50) {
            try {
                throw new FailedValidationException("Title of producer should contains less than 50 character");
            } catch (FailedValidationException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            return true;
        }
    }

    public boolean isValidDateFormat(String date) {
        LocalDateTime ldt = null;

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        ldt = LocalDateTime.parse(date, formatter);
            String res = ldt.format(formatter);

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
}