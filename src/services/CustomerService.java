package services;

import entities.Customer;
import entities.Gender;
import exceptions.FailedValidationException;
import validation.Validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static utils.FileUtils.readFromFile;

public class CustomerService {
    private final Locale locale = new Locale("en");
    private final DateTimeFormatter CUSTOMER_DOB = DateTimeFormatter.ofPattern("d MMMM yyyy", locale);

    public List<Customer> getAll(String path) {
        List<Customer> customers = new ArrayList<>();
        List<String> dataList = readFromFile(path);
        String customerName = null;
        LocalDate doB = null;
        Gender gender = null;
        String phoneNumber = null;
        try {
            for (int i = 1; i < dataList.size(); i++) {
                String[] array = dataList.get(i).split(";");

                //name validation
                if (Validation.isValidStringLength(array[0], 30)) {
                    customerName = array[0];
                }

                //date of birth format validation
                if (Validation.isValidDateFormat(array[1], CUSTOMER_DOB)) {
                    doB = LocalDate.parse(array[1], CUSTOMER_DOB);
                }

                //TODO address validation
                String address = array[2];

                //gender validation
                if (Validation.isValidGender(array[3].toUpperCase(Locale.ROOT))) {
                    gender = Gender.valueOf(array[3].toUpperCase(Locale.ROOT));
                }
                if (!array[4].isEmpty()) {
                    //phone number validation
                    if (Validation.isValidPhoneNumber(array[4])) {
                        phoneNumber = array[4];
                    }
                }
                customers.add(new Customer(customerName, doB, address, gender, phoneNumber));
            }
        } catch (FailedValidationException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public Customer getByName(String name, String path) {
        for (Customer cus : getAll(path)) {
            if (cus.getName().equals(name)) {
                System.out.println(cus);
                return cus;
            }
        }
        return null;
    }
}

//    public List<Customer> getListOfCustomersByGender(Gender gender) {
//        List<Customer> customerList = new ArrayList<>();
//        for (Customer cus : Customer.getCustomerList()) {
//            if (cus.getGender().equals(gender)) {
//                customerList.add(cus);
//            }
//        }
//        return customerList;
//    }
//
//    public List<Integer> getIdListOfPurchasesByGender(Gender gender) {
//        List<Integer> idListOfFemalePurchases = new ArrayList<>();
//        for (Customer cus : getListOfCustomersByGender(gender)) {
//            for (int id : cus.getLastPurchases()) {
//                idListOfFemalePurchases.add(id);
//            }
//        }
//        return idListOfFemalePurchases;
//    }