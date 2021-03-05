package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private int id;
    private String name;
    private LocalDate doB;
    private String address;
    private Gender gender;
    private String phoneNumber;
    private List<Order> orderList = new ArrayList<>();

    public Customer(String name, LocalDate doB, String address, Gender gender, String phoneNumber) {
        this.name = name;
        this.doB = doB;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public Customer(int id, String name, LocalDate doB, String address, Gender gender, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.doB = doB;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDoB() {
        return doB;
    }

    public void setDoB(LocalDate doB) {
        this.doB = doB;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && Objects.equals(name, customer.name) && Objects.equals(doB, customer.doB) && Objects.equals(address, customer.address) && gender == customer.gender && Objects.equals(phoneNumber, customer.phoneNumber) && Objects.equals(orderList, customer.orderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, doB, address, gender, phoneNumber, orderList);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", doB=" + doB +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", orderList=" + orderList +
                '}';
    }
}
