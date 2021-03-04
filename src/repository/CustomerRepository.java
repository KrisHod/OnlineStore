package repository;

import entities.Customer;
import entities.Gender;
import utils.DBUtil;

import java.sql.*;
import java.time.LocalDate;


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
        String sql = "SELECT id FROM customer WHERE name='" + customer.getName() + "'";
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

    private Customer getCustomer(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        LocalDate doB = rs.getDate("doB").toLocalDate();
        String address = rs.getString("address");
        Gender gender = Gender.valueOf(rs.getString("gender"));
        String phoneNumber = rs.getString("phoneNumber") == null ? null : rs.getString("phoneNumber");

        return new Customer(id, name, doB, address, gender, phoneNumber);
    }

    public Customer getById(int id) {
        Customer customer = null;
        String sql = "SELECT * FROM customer WHERE id=" + id;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                customer = getCustomer(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return customer;
    }
}
