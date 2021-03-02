package repository;

import entities.Customer;
import entities.Order;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OrderRepository {
    CustomerRepository customerRepository = new CustomerRepository();

    public void add(Order order) {
        String sql = "INSERT INTO order (customerId, date) values(?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, customerRepository.getId(order.getCustomer()));
            stmt.setTimestamp(2, Timestamp.valueOf(order.getDateOrder().atStartOfDay()));

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }
}
