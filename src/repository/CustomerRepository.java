package repository;

import entities.Customer;
import utils.DBUtil;

import java.sql.*;

public class CustomerRepository {
    public void add(Customer customer) {
        String sql = "INSERT INTO customer (name, doB, address, gender, phoneNumber) values(?, ?, ?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setTimestamp(2, Timestamp.valueOf(customer.getDoB().atStartOfDay()));
            stmt.setString(3, customer.getAddress());
            stmt.setString(4, String.valueOf(customer.getGender()));
            stmt.setString(5, customer.getPhoneNumber());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public Integer getId(Customer customer) {
        int id = 0;
        String sql = "SELECT id FROM customer WHERE name= " + customer.getName();
        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return id;
    }
}
