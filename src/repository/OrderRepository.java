package repository;

import entities.Customer;
import entities.Item;
import entities.Order;
import utils.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    CustomerRepository customerRepository = new CustomerRepository();
    OrderedItemsRepository orderedItemsRepository = new OrderedItemsRepository();

    public void add(Order order) {
        String sql = "INSERT INTO `order` (customerId, date) values(?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, customerRepository.getId(order.getCustomer()));
            stmt.setTimestamp(2, Timestamp.valueOf(order.getDateOrder().atStartOfDay()));

            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    private Order getOrder(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int customerId = rs.getInt("customerId");
        Customer customer = customerRepository.getById(customerId);
        List<Item> items = orderedItemsRepository.getById(id);
        LocalDate dateOrder = rs.getDate("date").toLocalDate();
        return new Order(id, customer, items, dateOrder);
    }

    public Order getById(int id) {
        Order order = null;
        String sql = "SELECT * FROM order WHERE id=" + id;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                order = getOrder(rs);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return order;
    }

    public List<Order> getAll() {
        List<Order> merchants = new ArrayList<>();
        String sql = "SELECT * FROM `order` ";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                merchants.add(getOrder(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return merchants;
    }
}
