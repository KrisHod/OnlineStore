package services;

import entities.Gender;
import entities.Item;
import entities.Order;
import repository.OrderedItemsRepository;

import java.util.ArrayList;
import java.util.List;

public class OrderedItemsService {
    private OrderedItemsRepository orderedItemsRepository;
    private OrderService orderService;

    public OrderedItemsService() {
        init();
    }

    private void init() {
        this.orderedItemsRepository = new OrderedItemsRepository();
        this.orderService = new OrderService();
    }

    public void addAllToDB(List<Order> orders) {
        for (Order or : orders) {
            if (!isInDB(or)) {
                orderedItemsRepository.add(or);
            }
        }
    }

    public boolean isInDB(Order order) {
        return order.equals(orderedItemsRepository.getById(order.getId()));
    }

    public List<Item> getById(int orderId) {
        return orderedItemsRepository.getById(orderId);
    }

    public List<Order> getAll() {
        return orderedItemsRepository.getAll();
    }

    public List<Item> getByGender(Gender gender) {
        List<Item> sortedItems = new ArrayList<>();
        List<Order> sortedOrders = orderService.getByGender(gender);

        for (Order or : sortedOrders) {
            sortedItems.addAll(getById(or.getId()));
        }
        return sortedItems;
    }
}

