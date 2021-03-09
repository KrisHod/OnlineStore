package entities;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Order {
    private int id;
    private Customer customer;
    private List<Item> itemList;
    private LocalDate dateOrder;

    public Order(Customer customer, List<Item> itemList, LocalDate dateOrder) {
        this.customer = customer;
        this.itemList = itemList;
        this.dateOrder = dateOrder;
    }

    public Order(int id, Customer customer, LocalDate dateOrder) {
        this.id = id;
        this.customer = customer;
        this.dateOrder = dateOrder;
    }

    public Order(int id, List<Item> itemList, LocalDate dateOrder) {
        this.id = id;
        this.itemList = itemList;
        this.dateOrder = dateOrder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return getId() == order.getId() && Objects.equals(getCustomer(), order.getCustomer()) && Objects.equals(getItemList(), order.getItemList()) && Objects.equals(getDateOrder(), order.getDateOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCustomer(), getItemList(), getDateOrder());
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", itemList=" + itemList +
                ", dateOrder=" + dateOrder +
                '}';
    }
}
