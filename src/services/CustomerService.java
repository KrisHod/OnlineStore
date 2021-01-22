package services;

import entities.Customer;
import entities.Gender;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    public List<Customer> getListOfCustomersByGender(Gender gender) {
        List<Customer> customerList = new ArrayList<>();
        for (Customer cus : Customer.getCustomerList()) {
            if (cus.getGender().equals(gender)) {
                customerList.add(cus);
            }
        }
        return customerList;
    }

    public List<Integer> getIdListOfPurchasesByGender(Gender gender) {
        List<Integer> idListOfFemalePurchases = new ArrayList<>();
        for (Customer cus : getListOfCustomersByGender(gender)) {
            for (int id : cus.getLastPurchases()) {
                idListOfFemalePurchases.add(id);
            }
        }
        return idListOfFemalePurchases;
    }

}
