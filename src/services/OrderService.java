package services;

import entities.Customer;
import entities.Gender;
import entities.Order;
import repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderService {
    private OrderRepository orderRepository = new OrderRepository();
    private CustomerService customerService = new CustomerService();

    public OrderService() {
        init();
    }

    private void init() {
        this.orderRepository = new OrderRepository();
        this.customerService = new CustomerService();
    }

    public void addAllToDB(List<Order> orders) {
        for (Order or : orders) {
            orderRepository.add(or);
        }
    }

    public List<Order> getAll() {
        return orderRepository.getAll();
    }

    public List<Order> getByGender(Gender gender) {
        List<Order> sortedByGender = new ArrayList<>();
        List<Customer> sortedCustomers = customerService.getByGender(gender);

        for (Order or : getAll()) {
            for (Customer cus : sortedCustomers) {
                if (or.getCustomer().getId() == cus.getId()) {
                    sortedByGender.add(or);
                }
            }
        }
        return sortedByGender;
    }
}
