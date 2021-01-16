package services;

import entities.Customer;
import entities.Gender;
import entities.Item;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CustomerService {

    public List<Customer> getListOfFemaleCustomers() {
        List<Customer> listOfFemaleCustomers = new ArrayList<>();
        for (Customer cus : Customer.getCustomerList()) {
            if (cus.getGender().equals(Gender.FEMALE)) {
                listOfFemaleCustomers.add(cus);
            }
        }
        return listOfFemaleCustomers;
    }

    public List<Integer> getIdListOfFemalePurchases() {
        List<Integer> idListOfFemalePurchases = new ArrayList<>();
        for (Customer cus : getListOfFemaleCustomers()) {
            for (int id : cus.getLastPurchases()) {
                idListOfFemalePurchases.add(id);
            }
        }
        return idListOfFemalePurchases;
    }

    public int getIdOfTheMostPopularGood() {
        List<Integer> idListOfFemalePurchases = getIdListOfFemalePurchases();
        if (idListOfFemalePurchases == null) {
            return 0;
        }
        idListOfFemalePurchases.sort(Comparator.naturalOrder());

        int previous = idListOfFemalePurchases.get(0);
        int popular = idListOfFemalePurchases.get(0);
        int count = 1;
        int maxCount = 1;

        for (int i = 1; i < idListOfFemalePurchases.size(); i++) {
            if (idListOfFemalePurchases.get(i) == previous)
                count++;
            else {
                if (count > maxCount) {
                    popular = idListOfFemalePurchases.get(i - 1);
                    maxCount = count;
                }
                previous = idListOfFemalePurchases.get(i);
                count = 1;
            }
        }

        return count > maxCount ? idListOfFemalePurchases.get(idListOfFemalePurchases.size() - 1) : popular;
    }
}
