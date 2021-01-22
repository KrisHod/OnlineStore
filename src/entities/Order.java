package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Order {
    private int number;
    private Customer customer;
    private List<Item> itemList = new ArrayList<>();
    private LocalDate dateOrder;

    public Order(int number, Customer customer, List<Item> itemList, LocalDate dateOrder) {
        this.number = number;
        this.customer = customer;
        this.itemList = itemList;
        this.dateOrder = dateOrder;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public LocalDate getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDate dateOrder) {
        this.dateOrder = dateOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getNumber() == order.getNumber() && Objects.equals(getCustomer(), order.getCustomer()) && Objects.equals(getItemList(), order.getItemList()) && Objects.equals(getDateOrder(), order.getDateOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getCustomer(), getItemList(), getDateOrder());
    }

    @Override
    public String toString() {
        return "Order{" +
                "number=" + number +
                ", customer=" + customer +
                ", itemList=" + itemList +
                ", dateOrder=" + dateOrder +
                '}';
    }
}
