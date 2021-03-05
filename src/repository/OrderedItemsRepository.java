package repository;

import entities.Customer;
import entities.Item;
import entities.Order;
import utils.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderedItemsRepository {
    private ItemRepository itemRepository;
    private CustomerRepository customerRepository;

    public OrderedItemsRepository() {
        init();
    }

    private void init() {
        this.itemRepository = new ItemRepository();
        this.customerRepository = new CustomerRepository();
    }

    public void add(Order order) {
        String sql = "INSERT INTO orderedItems (date, orderId, itemId) values(?, ?, ?)";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(order.getDateOrder().atStartOfDay()));
            stmt.setInt(2, order.getId());

            for (Item item : order.getItemList()) {
                stmt.setInt(3, item.getId());
                stmt.addBatch();
            }
            stmt.executeBatch();
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    public List<Item> getById(int orderId) {
        List<Item> items = new ArrayList<>();

        String sql = "SELECT * FROM orderedItems WHERE id=" + orderId;
        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Item item = itemRepository.getById(rs.getInt("itemId"));
                items.add(item);
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return items;
    }

    private Order getOrder(ResultSet rs) throws SQLException {
        LocalDate dateOrder = rs.getDate("date").toLocalDate();
        int orderId = rs.getInt("orderId");
        int customerId = rs.getInt("customerId");
        Customer customer = customerRepository.getById(customerId);
        List<Item> items = getById(orderId);

        return new Order(orderId, customer, items, dateOrder);
    }

    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orderedItems ";

        try (Connection con = DBUtil.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                orders.add(getOrder(rs));
            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
        return orders;
    }
}
