package services;

import entities.Customer;
import fileReader.CustomerFileReader;


public class CustomerService {
CustomerFileReader customerFileReader = new CustomerFileReader();

    public Customer getByName(String name) {
        for (Customer cus : customerFileReader.getCustomers()) {
            if (cus.getName().equals(name)) {
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