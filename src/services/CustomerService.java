package services;

import entities.Customer;
import entities.Gender;
import entities.Item;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    public List<Customer> getListOfFemaleCustomers (){
        List<Customer> listOfFemaleCustomers = new ArrayList<>();
        for (Customer cus: Customer.getCustomerList()) {
            if(cus.getGender().equals(Gender.FEMALE)){
                listOfFemaleCustomers.add(cus);
            }
        }
        return listOfFemaleCustomers;
    }

    public List <Integer> getIdListOfFemalePurchases (){
        List<Integer> idListOfFemalePurchases = new ArrayList<>();
        for (Customer cus: getListOfFemaleCustomers()) {
            for (int id: cus.getLastPurchases()) {
                idListOfFemalePurchases.add(id);
            }
        }
        return idListOfFemalePurchases;
    }
    
}
