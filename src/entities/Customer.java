package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Customer {
    private String name;
    private LocalDate doB;
    private String address;
    private Gender gender;
    private String phoneNumber;
    private List <Order> orderList= new ArrayList<>();

    public Customer(String name, LocalDate doB, String address, Gender gender, String phoneNumber) {
        this.name = name;
        this.doB = doB;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
   //     getCustomerList().add(this);
    }

    public Customer(String name, LocalDate doB, String address, Gender gender) {
        this.name = name;
        this.doB = doB;
        this.address = address;
        this.gender = gender;
  //      getCustomerList().add(this);
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
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getName(), customer.getName()) && Objects.equals(getDoB(), customer.getDoB()) && Objects.equals(getAddress(), customer.getAddress())
                && getGender() == customer.getGender() && Objects.equals(getPhoneNumber(), customer.getPhoneNumber()) && Objects.equals(getOrderList(), customer.getOrderList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDoB(), getAddress(), getGender(), getPhoneNumber(), getOrderList());
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", DoB=" + doB +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
