package services;

import entities.Customer;
import entities.Item;
import entities.Order;
import exceptions.FailedValidationException;
import validation.Validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static utils.FileUtils.readFromFile;

public class OrderService {
    private final Locale locale = new Locale("en");
    private final DateTimeFormatter DATE_OF_LAST_PURCHASE = DateTimeFormatter.ofPattern("M/dd/yyyy", locale);
    private final DateTimeFormatter DATE_OF_LAST_PURCHASE2 = DateTimeFormatter.ofPattern("MM.dd.yyyy", locale);

    CustomerService customerService = new CustomerService();
    ItemService itemService = new ItemService();

    public List<Order> getAll(String customerPath, String itemPath) {
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
                    itemList.add(itemService.getById(Integer.parseInt(s), itemPath));
                }

                //date of last purchase format validation
                if (Validation.isValidDateFormat(array[6], DATE_OF_LAST_PURCHASE)) {
                    dateOrder = LocalDate.parse(array[6], DATE_OF_LAST_PURCHASE);
                    System.out.println(dateOrder);
                }
                else if (Validation.isValidDateFormat(array[6], DATE_OF_LAST_PURCHASE2)) {
                    dateOrder = LocalDate.parse(array[6], DATE_OF_LAST_PURCHASE2);
                }
                orders.add(new Order(customer, itemList, dateOrder));
            }
        } catch (FailedValidationException | NumberFormatException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
