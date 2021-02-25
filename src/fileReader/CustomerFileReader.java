package fileReader;

import entities.Customer;
import entities.Gender;
import entities.Item;
import entities.Order;
import exceptions.FailedValidationException;
import services.CustomerService;
import services.ItemService;
import validation.Validator;

import java.time.LocalDate;
import java.util.*;

import static utils.FileUtils.readFromFile;

public class CustomerFileReader {

    private List<Customer> customers = getAll(Constants.CUSTOMERS_PATH);

    public Map<String, List<Customer>> pathToCustomersList = Map.of(Constants.CUSTOMERS_PATH, customers);

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

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
                if (Validator.isValidStringLength(array[0], 30)) {
                    customerName = array[0];
                }

                //date of birth format validation
                if (Validator.isValidDateFormat(array[1], Constants.CUSTOMER_DOB)) {
                    doB = LocalDate.parse(array[1], Constants.CUSTOMER_DOB);
                }

                //TODO address validation
                String address = array[2];

                //gender validation
                if (Validator.isValidGender(array[3].toUpperCase(Locale.ROOT))) {
                    gender = Gender.valueOf(array[3].toUpperCase(Locale.ROOT));
                }
                if (!array[4].isEmpty()) {
                    //phone number validation
                    if (Validator.isValidPhoneNumber(array[4])) {
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

}
