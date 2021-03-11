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

    public void save(List<Order> orders) {
        for (Order or : orders) {
            orderedItemsRepository.add(or);
        }
    }

    public List<Item> getById(int orderId) {
        return orderedItemsRepository.getById(orderId);
    }

    public List<Order> getAll() {
        return orderedItemsRepository.getAll();
    }

    public List<Integer> getListOfOrdersId(List<Order> orders) {
        List<Integer> ids = new ArrayList<>();
        for (Order or : orders) {
            ids.add(or.getId());
        }
        return ids;
    }

    public List<Item> getByGender(Gender gender) {
        List<Item> sortedItems = new ArrayList<>();
        List<Order> sortedOrders = orderService.getByGender(gender);

        for (Integer i : getListOfOrdersId(sortedOrders)) {
            sortedItems.addAll(getById(i));
        }
        return sortedItems;
    }
}

