package fileReader;

import entities.Customer;
import entities.Item;
import entities.Order;
import exceptions.FailedValidationException;
import services.CustomerService;
import services.ItemService;
import validation.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.FileUtils.readFromFile;

public class OrderFileReader {
    CustomerService customerService = new CustomerService();
    ItemService itemService = new ItemService();

    private List<Order> orders;

    public Map<String, List<Order>> ordersCache;

    public OrderFileReader() {
        this.ordersCache = new HashMap<>();
        init();
    }

    private void init() {
        getAll(Constants.CUSTOMERS_PATH, Constants.ITEMS_PATH);
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getAll(String customerPath, String itemPath) {
        if (ordersCache.get(customerPath) != null) {
            return ordersCache.get(customerPath);
        }

        List<Order> orders = new ArrayList<>();
        List<String> dataList = readFromFile(customerPath);

        Customer customer;
        LocalDate dateOrder = null;

        try {
            for (int i = 1; i < dataList.size(); i++) {
                List<Item> itemList = new ArrayList<>();
                String[] array = dataList.get(i).split(";");

                customer = customerService.getByName(array[0], customerPath);

                String[] lastPurchases = array[5].split(",");
                for (String s : lastPurchases) {
                    itemList.add(itemService.getByIdFromFile(Integer.parseInt(s), itemPath));
                }

                //date of last purchase format validation
                if (Validator.isValidDateFormat(array[6], Constants.DATE_OF_LAST_PURCHASE)) {
                    dateOrder = LocalDate.parse(array[6], Constants.DATE_OF_LAST_PURCHASE);
                } else if (Validator.isValidDateFormat(array[6], Constants.DATE_OF_LAST_PURCHASE2)) {
                    dateOrder = LocalDate.parse(array[6], Constants.DATE_OF_LAST_PURCHASE2);
                }
                orders.add(new Order(customer, itemList, dateOrder));
            }
        } catch (FailedValidationException | NumberFormatException e) {
            e.printStackTrace();
        }
        ordersCache.put(customerPath, orders);
        return orders;
    }
}
