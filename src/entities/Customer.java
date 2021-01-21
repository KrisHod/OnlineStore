package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Customer {
    private String name;
    private LocalDate doB;
    private String address;
    private Gender gender;
    private String phoneNumber;
    private int [] lastPurchases;
    private LocalDate dateOfLastPurchase;

    public Customer(String name, LocalDate doB, String address, Gender gender,
                    String phoneNumber, int[] lastPurchases, LocalDate dateOfLastPurchase) {
        this.name = name;
        this.doB = doB;
        this.address = address;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.lastPurchases = lastPurchases;
        this.dateOfLastPurchase = dateOfLastPurchase;
        getCustomerList().add(this);
    }

    public Customer(String name, LocalDate doB, String address, Gender gender,
                    int[] lastPurchases, LocalDate dateOfLastPurchase) {
        this.name = name;
        this.doB = doB;
        this.address = address;
        this.gender = gender;
        this.lastPurchases = lastPurchases;
        this.dateOfLastPurchase = dateOfLastPurchase;
        getCustomerList().add(this);
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

    public int[] getLastPurchases() {
        return lastPurchases;
    }

    public void setLastPurchases(int[] lastPurchases) {
        this.lastPurchases = lastPurchases;
    }

    public LocalDate getDateOfLastPurchase() {
        return dateOfLastPurchase;
    }

    public void setDateOfLastPurchase(LocalDate dateOfLastPurchase) {
        this.dateOfLastPurchase = dateOfLastPurchase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getGender() == customer.getGender() && Objects.equals(getName(), customer.getName()) && Objects.equals(getDoB(), customer.getDoB()) && Objects.equals(getAddress(), customer.getAddress()) && Objects.equals(getPhoneNumber(), customer.getPhoneNumber()) && Arrays.equals(getLastPurchases(), customer.getLastPurchases()) && Objects.equals(getDateOfLastPurchase(), customer.getDateOfLastPurchase());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getName(), getDoB(), getAddress(), getGender(), getPhoneNumber(), getDateOfLastPurchase());
        result = 31 * result + Arrays.hashCode(getLastPurchases());
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", DoB=" + doB +
                ", address='" + address + '\'' +
                ", gender=" + gender +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", lastPurchases=" + Arrays.toString(lastPurchases) +
                ", dateOfLastPurchase=" + dateOfLastPurchase +
                '}';
    }

    /**
     * this part of code will soon be replaced in DB class
     */
    private static List<Customer> customerList = new ArrayList<>();

    public static List<Customer> getCustomerList() {
        return customerList;
    }

    public static void setCustomerList(List<Customer> customerList) {
        Customer.customerList = customerList;
    }
}
