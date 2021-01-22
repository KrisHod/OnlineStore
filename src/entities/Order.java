package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int number;
    private Customer customer;
    private List<Item> itemList = new ArrayList<>();
    private LocalDate dateOrder;
}
