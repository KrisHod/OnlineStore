package services;

import entities.Customer;
import entities.Gender;
import fileReader.CustomerFileReader;
import repository.CustomerRepository;

import java.util.List;

public class CustomerService {
    private CustomerFileReader customerFileReader;
    private CustomerRepository customerRepository;

    public CustomerService() {
        init();
    }

    private void init() {
        this.customerFileReader = new CustomerFileReader();
        this.customerRepository = new CustomerRepository();
    }

    public void save(List<Customer> customers) {
        for (Customer cus : customers) {
            customerRepository.add(cus);
        }
    }

    public Customer getByName(String name, String path) {
        for (Customer cus : customerFileReader.getAll(path)) {
            if (cus.getName().equals(name)) {
                return cus;
            }
        }
        return null;
    }

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    public List<Customer> getByGender(Gender gender) {
        return customerRepository.getByGender(gender);
    }
}